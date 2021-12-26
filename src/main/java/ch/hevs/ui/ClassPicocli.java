package ch.hevs.ui;

import ch.hevs.ui.commands.Decrypte;
import ch.hevs.ui.commands.Generate;
import picocli.CommandLine;

import java.awt.*;
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
        /**
         * In the main method of your class, use the CommandLine.execute method bootstrap your application.
         * This will parse the command line, handle errors, handle requests for usage and version help,
         * and invoke the business logic.
         */

        boolean isRunning;
        boolean choose;
        Scanner scan = new Scanner(System.in);
        int exitStatus;

       do{
            System.out.println("Do you want to generate parts ?");
            choose = scan.nextBoolean();

            if(choose)
            {
                exitStatus = new CommandLine(new Generate()).execute(args);
            } else {
                exitStatus = new CommandLine(new Decrypte()).execute(args);
            }
            System.out.println("Do you want to continue to use Shamir secret?");
            isRunning = scan.nextBoolean();
        }
        while(isRunning == true);

        System.out.println("Goodbye brotha");
        System.exit(exitStatus);
    }
}
