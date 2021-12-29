package ch.hevs.ui;

import ch.hevs.parameters.Config;
import ch.hevs.ui.commands.Decrypt;
import ch.hevs.ui.commands.Generate;
import picocli.CommandLine;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Using picocli for the command line user interface
 *
 * 2 options :
 * 1) Generate secret shares and get files of them.
 *      - Inputs    : #Bytes (modulo) / #Parts / Threshold (k <= #Parts)
 *      - Output    : #File based on #Parts. JSON file containing the shares of secrets
 *      - Process   : The degree of the polynomial will be = threshold k-1, and we will generate the shares a0, a1,..., an-1
 *                    Then we evaluate f(x) = ax^2 + bx +c (depends on the degree) --> for X
 *
 *
 * 2) Encrypt / Decrypt a file
 *      - Inputs    : #Parts (in file with their content in code) / File to process / EncryptOrDecrypt
 *      - Output    : Decrypted or encrypted input file according to choice
 *      - Process   : calculates the secret internally, it remains in memory
 *
 * @author Milena Lonfat
 */
public class ClassPicocli
{
    public static void main(String[] args) {
        System.out.println( "Welcome to the Shamirs Secret Sharing application. \n" +
                "This app can create parts stored in JSON files, and use those parts to encrypt or decrypt a choosen file.");

        Config.getConfig(); //Check that the environment variable exists

        boolean isRunning;
        int choose;
        Scanner scan = new Scanner(System.in);
        int exitStatus;

        do{
            System.out.println("Choose an option\n" +
                               "1) Generate parts\n" +
                               "2) Encrypt or Decrypt a file with parts");
            // Verify that input is an int !
            while(!scan.hasNextInt())
            {
                System.out.println("This option does not exist, try again");
                scan.next();
            }
            choose = scan.nextInt();

            switch (choose)
            {
                case 1:
                    int numberOfparams = 4;
                    String[] myArgs = new String[numberOfparams];
                    myArgs[0] = "-g";

                    System.out.println("Enter number of byte: 16 , 24 or 32");
                    myArgs[1] = scan.next();

                    System.out.println("Enter number of parts to generate :");
                    myArgs[2] = scan.next();

                    System.out.println("Enter threshold for security");
                    myArgs[3] = scan.next();

                    exitStatus = new CommandLine(new Generate()).execute(myArgs);
                    break;

                case 2:
                    // NUMBER OF PARTS TO USE           ***********************************
                    System.out.println("How many parts do you want to use ? It should be the same amount as the threshold you created them with");
                    // Verify that input is integer !
                    int nbParts;
                    do
                    {
                        while(!scan.hasNextInt() )
                        {
                            System.out.println("Number of parts to use entered is too low or too high, or your value contains letters.");
                            System.out.println("Remember, it should be the same amount of parts as our threshold value !");
                            scan.next();
                        }
                        nbParts = scan.nextInt();
                        if (nbParts<2 || nbParts>2000000)
                        {
                            System.out.println("Number of parts to use can be between 2 and 2'000'000, re-try !");
                        }
                    }while (nbParts<2 || nbParts>2000000);

                    //Path[] paths = new Path[nbParts];



                    String[] myArgs2 = new String[3];

                    System.out.println("Do you want to encrypt -e or decrypt -d the file ?");
                    // Verify that input is an -e or -d !
                    while(!scan.hasNext("-e") && !scan.hasNext("-d") )
                    {
                        System.out.println("This option does not exist, try again");
                        scan.next();
                    }
                    myArgs2[0] = scan.next();


                    // PARTS ON THE DRIVE TO SELECT     ***********************************
                    myArgs2[1] = "";

                    for(int i = 0; i < nbParts/*paths.length*/; i++)
                    {
                        System.out.println("Enter the path of the file: "+ i);
                        String path = scan.next();
                        myArgs2[1] = myArgs2[1]+Paths.get(path)+" ";
                    }

                    System.out.println("Enter a file to encrypt/decrypt");
                    String file = scan.next();
                    myArgs2[2] = file;

                    exitStatus = new CommandLine(new Decrypt()).execute(myArgs2);

                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    exitStatus = 1; // Error code obtained for this type of error
                    break;

            }

            System.out.println();
            System.out.println("DO YOU WANT TO CONTINUE TO USE SHAMIRS SECRET SHARING APP ?");
            // Verify that input is an int !
            while (!scan.hasNextBoolean())
            {
                System.out.println("Choose between true / false !");
                scan.next();
            }
            isRunning = scan.nextBoolean();
        }
        while(isRunning == true);

        System.out.println("Goodbye");
        System.exit(exitStatus);
    }
}

