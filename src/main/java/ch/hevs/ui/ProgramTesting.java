package ch.hevs.ui;

import ch.hevs.maths.LagrangeInterpolation;
import ch.hevs.errors.BusinessException;
import ch.hevs.storage.JsonPartsFiles;
import ch.hevs.tools.crypt.FileEncryption;
import ch.hevs.tools.generateParts.UserParts;
import ch.hevs.tools.RegenerateParts.AssembleParts;

import java.io.File;


public class ProgramTesting {

    public static void main(String[] args) throws BusinessException {

        // Test avec 3 fichiers json pour les part de secrets
        JsonPartsFiles jpf = new JsonPartsFiles();


        File myPart1 = new File(args[0]);
        File myPart2 = new File(args[1]);
        File myPart3 = new File(args[2]);

        File[] usersFiles = new File[3];

        // file à crypter decrypter
        File myFileDecryptEncrypt = new File(args[3]);

        jpf.read(myPart1);
        //System.out.println(jpf.getUserParts());
        jpf.read(myPart2);
        //System.out.println(jpf.getUserParts());
        jpf.read(myPart3);
        //System.out.println(jpf.getUserParts());

        usersFiles[0] = myPart1;
        usersFiles[1] = myPart2;
        usersFiles[2] = myPart3;

        regenerateWithGivenParts(usersFiles, myFileDecryptEncrypt, true);

    }

    public static void regenerateWithGivenParts(File[] usersFiles, File fileToCryptDecrypt, boolean toEncrypt) throws BusinessException
    {
        // objets utiles

        AssembleParts as = new AssembleParts();
        FileEncryption fe = new FileEncryption();

        UserParts[] usersParts;
        int nbUsersParts;

        String homePath = System.getenv("HOME"); // pour avoir une string contenant le chemin absolu du desktop user

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
        as.secret(usersParts);
        as.affiche(as.getSecret());

        // *** ETAPE 2 : CHOIX DE L'OPTION ET CRYPTAGE OU DECRYPTAGE DU FICHIER PDF OU WORD
        if (toEncrypt)
        {
            fe.encrypt(as.getSecret(), fileToCryptDecrypt, new File(homePath));
        }
        else {
            fe.decrypt(as.getSecret(), fileToCryptDecrypt, new File(homePath));
        }
    }

    private static void afficheParts(UserParts[] usersParts){
        for (int i = 0; i < usersParts.length; i++)
        {
            for (int j = 0; j < 32; j++)
            {
                System.out.print(usersParts[i].getPartsByUser().get(j));
            }
            System.out.println();
        }
    }

}
