package ch.hevs.tools.reconstructParts;

import ch.hevs.errors.BusinessException;
import ch.hevs.parameters.Config;
import ch.hevs.storage.serializationTool.JsonPartsFiles;
import ch.hevs.tools.crypting.FileEncryption;
import ch.hevs.storage.UserParts;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.security.Security;

/**
 * Class for encryption or decryption of any file type (.pdf, .exe, ...)
 *
 * @author Jonathan Bourquin, Elias Borrajo
 */

public class EncryptionDecryption {
    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************

    /**
     * Encryption or decryption of a file
     *
     * @param usersFiles
     * @param fileToCryptDecrypt
     * @param toEncrypt
     */
    public EncryptionDecryption(File[] usersFiles, File fileToCryptDecrypt, boolean toEncrypt) {
        Security.addProvider(new BouncyCastleProvider());

        FileEncryption fe = new FileEncryption();
        UserParts[] usersParts;
        int nbUsersParts;

        String extension = readExtensionFile(fileToCryptDecrypt);

        String homePath = Config.getConfig().getStorePath();    //System.getenv("SHAMIR"); // pour avoir une string contenant le chemin absolu de la variable environnement HOME
        String absolutePathOutputFileEncryption = homePath + "\\fileEncrypted." + extension; // chemin absolu pour le fichier tmp de cryptage // TODO : Chemin hardcodé WINDOWS ICI
        String absolutePathOutputFileDecryption = homePath + "\\fileDecrypted." + extension; // chemin absolu pour le fichier tmp de décryptage // TODO : Chemin hardcodé WINDOWS ICI

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

        // test du seuil pour voir si le nombre de parts entrées est suffisante
        if (usersParts.length >= usersParts[0].getThreshold()) {
            // initialise la classe pour l'assemblage avec comme entrée au constructeur le nb de Point --> donne le nb de bytes sur lequel boucler
            //AssembleParts as = new AssembleParts(usersParts[1].getParts().length);

            // 2) Build du secret pour obtenir l'array de byte de tous les secrets (tous les f())
            // --> à aller chercher dans la classe AssembleParts
            AssembleParts as = new AssembleParts(usersParts[0].getPartsByUser().size());

            as.secret(usersParts);
            //System.out.println("Secret :");
            //as.affiche(as.getSecret());

            // *** ETAPE 2 : CHOIX DE L'OPTION ET CRYPTAGE OU DECRYPTAGE DU FICHIER PDF OU WORD
            if (toEncrypt) {
                try {
                    fe.encrypt(as.getSecret(), fileToCryptDecrypt, new File(absolutePathOutputFileEncryption));
                } catch (BusinessException e) {
                    throw new IllegalArgumentException("FAILED TO EN-CRYPT DATA : " +
                            "\n Verify your files path given as input !" +
                            "\n" + e);
                }

                fileToCryptDecrypt.delete(); // suppression de l'ancien fichier après exécution
            } else {
                try {
                    fe.decrypt(as.getSecret(), fileToCryptDecrypt, new File(absolutePathOutputFileDecryption));
                } catch (BusinessException e) {
                    throw new IllegalArgumentException("FAILED TO DE-CRYPT DATA : " +
                            "\n Verify your files path given as input !" +
                            "\n" + e);

                }

                fileToCryptDecrypt.delete();
            }

        } else {
            throw new IllegalArgumentException("Wrong threshold " + usersParts[0].getThreshold() + " parts are needed");
        }
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************

    /**
     * Allows to read the extension of a file
     *
     * @param fileToCryptDecrypt
     * @return
     */
    private static String readExtensionFile(File fileToCryptDecrypt) {
        System.out.println("File to de/en-crypt is : " + fileToCryptDecrypt.getName());

        int extensionIndex = fileToCryptDecrypt.getName().lastIndexOf(".") + 1;

        String extension = fileToCryptDecrypt.getName().substring(extensionIndex);
        //System.out.println(extension);
        return extension;
    }


    /*private static void afficheParts(UserParts[] usersParts){
        for (int i = 0; i < usersParts.length; i++)
        {
            for (int j = 0; j < 32; j++)
            {
                System.out.print(usersParts[i].getPartsByUser().get(j));
            }
            System.out.println();
        }
    }*/

    /*
    public static void main(String[] args) throws BusinessException
    {


        // Test avec 4 fichiers json pour les part de secrets ATTENTION : si le seuil fixé à la génération des parts est pas atteint le programme ne marche pas

        // exemple cryptage d'un fichier .pdf
        JsonPartsFiles jpf = new JsonPartsFiles();


        File myPart1 = new File(args[0]);
        File myPart2 = new File(args[1]);
        File myPart3 = new File(args[2]);
        File myPart4 = new File(args[3]);

        File[] usersFiles = new File[4];


        // file à crypter decrypter
        File myFileDecryptEncrypt = new File(args[4]);

        jpf.read(myPart1);
        //System.out.println(jpf.getUserParts());
        jpf.read(myPart2);
        //System.out.println(jpf.getUserParts());
        jpf.read(myPart3);
        //System.out.println(jpf.getUserParts());
        jpf.read(myPart4);
        //System.out.println(jpf.getUserParts());

        usersFiles[0] = myPart1;
        usersFiles[1] = myPart2;
        usersFiles[2] = myPart3;
        usersFiles[3] = myPart4;

        new EncryptionDecryption(usersFiles, myFileDecryptEncrypt, true);

        try
        {
            TimeUnit.SECONDS.sleep(25);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // décryptage du même fichier .pdf avec des users parts différents
        File myPart11 = new File(args[5]);
        File myPart22 = new File(args[6]);
        File myPart33 = new File(args[7]);
        File myPart44 = new File(args[8]);

        jpf.read(myPart11);
        //System.out.println(jpf.getUserParts());
        jpf.read(myPart22);
        //System.out.println(jpf.getUserParts());
        jpf.read(myPart33);
        //System.out.println(jpf.getUserParts());

        File[] usersFiles_V2 = new File[4];

        usersFiles_V2[0] = myPart11;
        usersFiles_V2[1] = myPart22;
        usersFiles_V2[2] = myPart33;
        usersFiles_V2[3] = myPart44;


        File myFileDecryptEncrypt_V2 = new File(args[9]);
        //System.out.println(myFileDecryptEncrypt.getAbsolutePath());

        new EncryptionDecryption(usersFiles_V2, myFileDecryptEncrypt_V2, false);

    }
*/

}
