package ch.hevs.ui;

import ch.hevs.parameters.Config;
import ch.hevs.ui.commands.Decrypte;
import ch.hevs.ui.commands.Generate;
import picocli.CommandLine;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Utilise le package PICOCLI pour faciliter l'USER INTERFACE en ligne de commande.
 *
 * On va pouvoir avoir 2 option :
 *
 * 1) Génerer les parts de secrets et en obtenir un fichier.
 *      - Entrées   : #Bytes (modulo) / #Parts (10 employés) / Seuil (k  <= # Parts)
 *      - Sortie    : #Fichier en fonction de #Parts. Fichier JSON contenant les parts de secrets
 *      - Process   : Le degrée du polynome sera = seuil k-1, et nous génerera les parts a0, a1,..., an-1
 *                    Puis on évalue f(x) = ax^2 + bx +c (dépend du degré) --> pour X
 *
 *
 * 2) Encrypter / Decrypter un fichier
 *      - Entrées   : # Parts(en fichier avec leur contenu en code) / Fichier à traiter / EncryptOrDecrypt
 *      - Sortie    : Fichier d'entrée Decrypté OU Encrypté selon choix.
 *      - Process   : Va calculer le secret en interne, il reste en mémoire.
 *
 */
public class ClassPicocli
{
    public static void main(String[] args) {
        System.out.println( "Welcome to the Shamirs Secret Sharing application. \n" +
                "This app can create parts stored in JSON files, and use those parts to encrypt or decrypt a choosen file.");

        Config.getConfig(); // Verifie que on a la variable d'environnement
        /**
         * In the main method of your class, use the CommandLine.execute method bootstrap your application.
         * This will parse the command line, handle errors, handle requests for usage and version help,
         * and invoke the business logic.
         */

        boolean isRunning;
        int choose;
        Scanner scan = new Scanner(System.in);
        int exitStatus;

        do{
            System.out.println("Choose option");
            System.out.println("1) Generate parts");
            System.out.println("2) Encrypt or Decrypt a file with parts");

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
                    // 1) Texte, dire ce que le user doit rentrer en params, mini mode d'emploi ?
                    // 2) Scanner des inputs du user, et les donner en args de execute ici
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

                        //paths[i] = Paths.get(path);

                        myArgs2[1] = myArgs2[1]+Paths.get(path)+" ";
                    }

                    System.out.println("Entrez fichier a crypter");
                    String file = scan.next();
                    myArgs2[2] = file;

                    exitStatus = new CommandLine(new Decrypte()).execute(myArgs2);

                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    exitStatus = 1; // Code d'erreur obtenue pour ce type d'erreur
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

