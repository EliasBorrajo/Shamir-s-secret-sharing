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

/**
 * Methods of encryption or decryption of a file
 */

public class EncryptionDecryption {
    /**
     * Encryption or decryption of a file
     * @param usersFiles
     * @param fileToCryptDecrypt
     * @param toEncrypt
     */
    public EncryptionDecryption(File[] usersFiles, File fileToCryptDecrypt, boolean toEncrypt)
    {
        Security.addProvider(new BouncyCastleProvider());
        FileEncryption fe = new FileEncryption();
        UserParts[] usersParts;
        int nbUsersParts;

        String extension = readExtensionFile(fileToCryptDecrypt);

        String homePath = Config.getConfig().getStorePath();                               // Environment variable
        String absolutePathOutputFileEncryption = homePath + "\\fileEncrypted."+extension; // Absolute path for the encryption tmp file
        String absolutePathOutputFileDecryption = homePath + "\\fileDecrypted."+extension; // Absolute path for the decryption tmp file

        //*** STEP 1: GENERATION OF THE SECRET (BYTES TABLE) FROM THE USERS' PARTS ***

        // 1) Deserialize usersFiles to put them in an array of UserParts[]
        // Get the number of user shares given as input to the program and create the array of userParts of the right length
        nbUsersParts = usersFiles.length; // # users' parts
        usersParts = new UserParts[nbUsersParts];

        JsonPartsFiles jpf = new JsonPartsFiles();

        // Deserialize the user shares to create the userParts objects to put in the table
        for (int i = 0; i < usersParts.length; i++)
        {
            usersParts[i] = jpf.read(usersFiles[i]);
        }

        // TODO 29.12.2021 JOOO ????? On peut supprimer ou pas?
        // Initialiser la classe pour l'assemblage avec comme entrée au constructeur le nb de Point --> donne le nb de bytes sur lequel boucler
        // AssembleParts as = new AssembleParts(usersParts[1].getParts().length);

        // 2) Build secret to get the byte array of all secrets (all f())
        // --> search in the class AssembleParts
        AssembleParts as = new AssembleParts(usersParts[0].getPartsByUser().size());

        as.secret(usersParts);
        System.out.println("Secret :");
        as.affiche(as.getSecret());

        // *** STEP 2: CHOOSING THE OPTION AND ENCRYPTING OR DECRYPTING THE FILE
        if (toEncrypt)
        {
            try {
                fe.encrypt(as.getSecret(), fileToCryptDecrypt, new File(absolutePathOutputFileEncryption));
            } catch (BusinessException e) {
                throw new IllegalArgumentException("Failed to encrypt data : "+e);
            }

            fileToCryptDecrypt.delete(); // Deleting the old file after execution
        }
        else {
            try {
                fe.decrypt(as.getSecret(), fileToCryptDecrypt, new File(absolutePathOutputFileDecryption));
            } catch (BusinessException e) {
                throw  new IllegalArgumentException("Failed to decrypt data : " + e);
            }
            fileToCryptDecrypt.delete();
        }
    }

    /**
     * Allows to read the extension of a file
     * @param fileToCryptDecrypt
     * @return
     */
    //TODO 29.12.2021 on fait quoi du yolo ici?
    private static String readExtensionFile(File fileToCryptDecrypt)
    {
        System.out.println(fileToCryptDecrypt.getName());

        int yolo = fileToCryptDecrypt.getName().lastIndexOf(".")+1;
        System.out.println(yolo);

        String extension = fileToCryptDecrypt.getName().substring(yolo);
        System.out.println(extension);
        return extension;
    }

    // TODO 29.12.2021 On garde ou on supprime?
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

    /*    public static void main(String[] args) throws BusinessException
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
    }*/

}
