package crypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.security.SecureRandom;
import java.security.Security;

import static org.junit.jupiter.api.Assertions.*;

public class FileEncryptionTest {

    private boolean compareFiles(File f1, File f2) throws IOException{
        boolean rv;

        // You may declare one or more resources in a try-with-resources statement
        try (BufferedReader br1 = new BufferedReader(new FileReader(f1));
             BufferedReader br2 = new BufferedReader(new FileReader(f2))) {

            while (true) {
                String c1, c2;
                c1 = br1.readLine();
                c2 = br2.readLine();

                if (c1 == null) {
                    // End of file f1. Does f2 have more lines?
                    rv = (c2 == null);
                    break;
                }
                rv = c1.equals(c2);

                if (!rv) {
                    // A different line in f1 and f2
                    break;
                }
            }
        }

        return rv;
    }
    @Test
    public void encrypt() {

        // Our provider is BouncyCastle (copy/paste the line below in your main method)
        Security.addProvider(new BouncyCastleProvider());

        SecureRandom rnd = new SecureRandom();
        byte[] myKey = new byte[16];
        rnd.nextBytes(myKey);

        File myFile = null;
        File copyOfMyFile = null;
        File myEncryptedFile = null;

        try {
            myFile = File.createTempFile("plaintext", ".txt");
            copyOfMyFile = File.createTempFile("plaintext_copy", ".txt");
            myEncryptedFile = File.createTempFile("ciphertext", ".txt");
        } catch (Exception e) {
            fail("Failed to create temporary files");
        }

        myFile.deleteOnExit();
        copyOfMyFile.deleteOnExit();
        myEncryptedFile.deleteOnExit();

        assertNotNull(myFile);
        assertNotNull(copyOfMyFile);
        assertNotNull(myEncryptedFile);

        String content = "To forget the law of constant change\n" +
                "Is like swimming against the tide\n" +
                "All passes, nothing remains\n" +
                "Yet our identity fictions abide\n";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(myFile))) {
            bw.write(content);
        } catch (Exception e) {
            fail("Unexpected error while writing");
        }

       /* try {
            //FileEncryption.encrypt(myKey, myFile, myEncryptedFile);
            //FileEncryption.decrypt(myKey, myEncryptedFile, copyOfMyFile);
            assertTrue(compareFiles(myFile, copyOfMyFile));
        } catch (Exception | BusinessException e) {
            System.out.println(e.getMessage());
            fail("Encryption and/or decryption failed");
        }*/


}
}
