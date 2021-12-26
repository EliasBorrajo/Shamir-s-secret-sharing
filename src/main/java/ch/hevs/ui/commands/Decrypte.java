package ch.hevs.ui.commands;

import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

@CommandLine.Command(
        name = "EncryptDecrypt",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Encrypt or decrypt file.s",
        optionListHeading = "%nOptions are:%n")

public class Decrypte implements Runnable{
    //*******************************************************************************
    //  A T T R I B U T S
    //*******************************************************************************
    // Decrypt
    @CommandLine.Option(names = { "-d", "--Decrypt" }, description = "Decrypt secret")
    boolean Decrypt = false;

    // Encrypt
    @CommandLine.Option(names = { "-e", "--Encrypt" }, description = "Decrypt secret")
    boolean Encrypt = false;

    // Files
    @CommandLine.Parameters(/* type = File.class, */ description = "the files to convert")
    File[] files; // picocli infers type from the generic type

    // Le fichier à décrypter
    @CommandLine.Parameters(paramLabel = "<FILE>",  description = "the files to convert")
    private String filePath = ""; // picocli infers type from the generic type

    //*******************************************************************************
    //  M E T H O D S
    //*******************************************************************************
    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        //A mettre avant?
        System.out.println("Decrypte/Encrypte runs");

        // Code de Jo pour decrypter
        System.out.println("How many file do you want to use?");
        int nbParts = scan.nextInt();

        Path[] paths = new Path[nbParts];

        for(int i = 0; i < nbParts; i++)
        {
            System.out.println("Enter the path of the file: ");

        }

    }
}
