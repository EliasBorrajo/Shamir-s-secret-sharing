package ch.hevs.tools.RegenerateParts;

import ch.hevs.errors.BusinessException;
import ch.hevs.errors.ErrorCode;
import ch.hevs.parameters.Config;
import ch.hevs.storage.JsonPartsFiles;
import ch.hevs.tools.crypt.FileEncryption;
import ch.hevs.tools.generateParts.UserParts;
import ch.hevs.tools.RegenerateParts.AssembleParts;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.security.Security;
import java.util.concurrent.TimeUnit;


public class EncryptionDecryption {

    public EncryptionDecryption(File[] usersFiles, File fileToCryptDecrypt, boolean toEncrypt)
    {
        Security.addProvider(new BouncyCastleProvider());
        FileEncryption fe = new FileEncryption();
        UserParts[] usersParts;
        int nbUsersParts;

        String extension = readExtensionFile(fileToCryptDecrypt);

        String homePath = Config.getConfig().getStorePath(); // pour avoir une string contenant le chemin absolu de la variable environnement HOME
        String absolutePathOutputFileEncryption = homePath + "\\fileEncrypted."+extension; // chemin absolu pour le fichier tmp de cryptage
        String absolutePathOutputFileDecryption = homePath + "\\fileDecrypted."+extension; // chemin absolu pour le fichier tmp de décryptage

        //*** ETAPE 1 : GENERATION DU SECRET (TABLEAU DE BYTES) A PARTIR DES PARTS DES USERS ***

        // 1) désérialisation des usersFiles pour les mettres dans un tableau de UserParts[]

        // récupère le nombre de parts utilisateur données en entrée du programme et créer le tableau de userParts de la bonne longueur
        nbUsersParts = usersFiles.length; // nb de part utilisateur
        usersParts = new UserParts[nbUsersParts];

        JsonPartsFiles jpf = new JsonPartsFiles();

        // désérialise les parts utilisateurs pour créer les objets userParts à mettre dans le tableau
        for (int i = 0; i < usersParts.length; i++) {
            usersParts[i] = jpf.read(usersFiles[i]);
        }

        // initialise la classe pour l'assemblage avec comme entrée au constructeur le nb de Point --> donne le nb de bytes sur lequel boucler
        //AssembleParts as = new AssembleParts(usersParts[1].getParts().length);

        // 2) Build du secret pour obtenir l'array de byte de tous les secrets (tous les f())
        // --> à aller chercher dans la classe AssembleParts
        AssembleParts as = new AssembleParts(usersParts[0].getPartsByUser().size());

        as.secret(usersParts);
        System.out.println("Secret :");
        as.affiche(as.getSecret());

        // *** ETAPE 2 : CHOIX DE L'OPTION ET CRYPTAGE OU DECRYPTAGE DU FICHIER PDF OU WORD
        if (toEncrypt)
        {
            try {
                fe.encrypt(as.getSecret(), fileToCryptDecrypt, new File(absolutePathOutputFileEncryption));
            } catch (BusinessException e) {
                throw  new IllegalArgumentException("Failed to encrypt data : "+e);
            }
            fileToCryptDecrypt.delete(); // suppression de l'ancien fichier après exécution
        }
        else {
            try {
                fe.decrypt(as.getSecret(), fileToCryptDecrypt, new File(absolutePathOutputFileDecryption));
            } catch (BusinessException e) {
                throw  new IllegalArgumentException("Failed to decrypt data : "+e);
            }
            fileToCryptDecrypt.delete();
        }
    }

    /**
     * Allows to read the extension of a file
     * @param fileToCryptDecrypt
     * @return
     */
    private static String readExtensionFile(File fileToCryptDecrypt)
    {
        System.out.println(fileToCryptDecrypt.getName());

        int yolo = fileToCryptDecrypt.getName().lastIndexOf(".")+1;
        System.out.println(yolo);
        String extension = fileToCryptDecrypt.getName().substring(yolo);

        System.out.println(extension);
        return extension;
    }
}