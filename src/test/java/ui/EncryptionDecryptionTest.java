package ui;

import ch.hevs.errors.BusinessException;
import ch.hevs.parameters.Config;
import ch.hevs.storage.JsonPartsFiles;
import ch.hevs.tools.generateParts.GenerateParts;

import java.io.File;

import static ch.hevs.ui.EncryptionDecryption.EncryptionDecryption;

public class EncryptionDecryptionTest {

    public static void main(String[] args) throws BusinessException
    {
        // Test avec 4 fichiers json pour les part de secrets ATTENTION : si le seuil fixé à la génération des parts est pas atteint le programme ne marche pas

        // exemple cryptage d'un fichier .pdf
        GenerateParts generateParts = new GenerateParts(32,10,4);
        JsonPartsFiles jpf = new JsonPartsFiles();

        String path = Config.getConfig().getStorePath();

        File myPart1 = new File(path+"\\User_1.json");
        File myPart2 = new File(path+"\\User_2.json");
        File myPart3 = new File(path+"\\User_3.json");
        File myPart4 = new File(path+"\\User_4.json");

        File[] usersFiles = new File[4];


        // file à crypter decrypter
        File myFileDecryptEncrypt = new File(path+"\\FileToEncrypt.pdf");

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

        EncryptionDecryption(usersFiles, myFileDecryptEncrypt, true);

        // décryptage du même fichier .pdf avec des users parts différents
        File myPart11 = new File(path+"\\User_5.json");
        File myPart22 = new File(path+"\\User_6.json");
        File myPart33 = new File(path+"\\User_7.json");
        File myPart44 = new File(path+"\\User_8.json");

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


        File myFileDecryptEncrypt_V2 = new File(path+"\\fileEncrypted.pdf");
        //System.out.println(myFileDecryptEncrypt.getAbsolutePath());

        EncryptionDecryption(usersFiles_V2, myFileDecryptEncrypt_V2, false);

    }
}
