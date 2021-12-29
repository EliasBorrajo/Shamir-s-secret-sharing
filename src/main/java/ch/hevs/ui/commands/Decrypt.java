package ch.hevs.ui.commands;

import ch.hevs.tools.RegenerateParts.EncryptionDecryption;
import picocli.CommandLine;
import java.io.File;

/**
 * Picocli informations
 * Encryption or decryption of a file
 */

@CommandLine.Command(
        name = "EncryptDecrypt",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Encrypt or decrypt file.s",
        optionListHeading = "%nOptions are:%n")

public class Decrypt implements Runnable{
    //*******************************************************************************
    //  A T T R I B U T E S
    //*******************************************************************************
    // Decrypt
    @CommandLine.Option( names = { "-d", "--Decrypt" }, description = "Decrypt secret")
    boolean decrypt;

    // Encrypt
    @CommandLine.Option( names = { "-e", "--Encrypt" }, description = "Decrypt secret")
    boolean encrypt;

    // Files
    @CommandLine.Parameters( split = " ", paramLabel = "FILE", description = "the files")
    String[] filesPath;

    //*******************************************************************************
    //  M E T H O D S
    //*******************************************************************************
    @Override
    public void run() {
        File file = new File(filesPath[filesPath.length-1]);

        File[] files = new File[filesPath.length-1];

        for(int i = 0; i < filesPath.length-1; i++)
        {
            files[i] = new File(filesPath[i]);
        }

        if(encrypt){
            System.out.println("Encrypt runs");
            EncryptionDecryption encryption = new EncryptionDecryption(files,file,encrypt);
        }
        if(decrypt) {
            System.out.println("Decrypt runs");
            EncryptionDecryption decryption = new EncryptionDecryption(files,file,!decrypt);
        }
    }
}

