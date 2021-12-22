package ch.hevs.tools.generateParts;

/**
 * 1) Génerer les parts de secrets et en obtenir un fichier.
 * - Entrées   : #Bytes (modulo) / #Parts (10 employés) / Seuil (k  <= # Parts)
 * - Sortie    : #Fichier en fonction de #Parts. Fichier JSON contenant les parts de secrets
 * - Process   : Le degrée du polynome sera = seuil k-1, et nous génerera les parts a0, a1,..., an-1
 * Puis on évalue f(x) = ax^2 + bx +c (dépend du degré) --> pour X
 */

import ch.hevs.maths.Polynome;
import ch.hevs.storage.JsonPartsFiles;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;

/**
 * OUTIL 1 : GENERATE PARTS
 * Va génerer les parts a0, a1 ......
 *
 * C'est cette classe qui av devoir faire le SHAMIR SECRET #N fois le #Bytes du tableau de bytes !
 *
 * Le tableau de bytes va avoir un modulo de 257 (premier NbrPremier au dessus de #Bytes).
 * Et va stocker les réponses des parts de secrets dans ses cases.
 * case [0] --> Part A1
 *              Part B1
 *              Part C1
 *
 * case [1] --> Part A2
 *              Part B2
 *              part C2
 *
 * etc... pour chaques cases.
 *
 * Puis tous les parts A composeront le code du fichier de la part N°1 = A,
 * puis B pour part N°2 = B etc...
 *
 */


public class GenerateParts
{
    private int nbrBytes;
    private int nbrParts;
    private int threshold;
    private static final int MAX_VALUE_INPUT = 2000000; // Arbitrary value for the max value of parts
    private static final int BYTESVALUE_16 = 16;
    private static final int BYTESVALUE_24 = 24;
    private static final int BYTESVALUE_32 = 32;


    public GenerateParts(int nbrBytesInput, int nbrPartsInput, int thresholdInput)
    {
        try
        {
            verifyInputs(nbrBytesInput, nbrPartsInput, thresholdInput);
        }
        catch (IllegalArgumentException exception)
        {
            System.out.println("COMMAND : \"GENERATE PARTS\" HAS STOPPED BECAUSE OF INVALID PARAMETERS ");
            return;
        }


        // If we get here, there has been NO ERRORS! So we initialize those parameters in attributes ! :)
        this.nbrBytes = nbrBytesInput;
        this.nbrParts = nbrPartsInput;
        this.threshold= thresholdInput;

        generatePartsAndSerialize();

        System.out.println("OPERATION DONE SUCCESFULLY ! -->  Parts for "+ (nbrParts-1) +" users have been written on yur hardware !");
        System.out.println("Follow the next instructions : ");
        System.out.println("1) For each user, send him only one file named \"User_X\".");
        System.out.println("2) After having send all the files, DESTROY the remaining folder.");
    }

    private void generatePartsAndSerialize()
    {

        // tableau de polynomes, il va stocker nos coordonées X&Y pour chaque User
        // Taille = nbr de bytes, car nbrBytes = le nombre de fois que on fait shamir secret
        Polynome[] polynomes = new Polynome[nbrBytes];   // Stockera x & y.| Chaque index --> une courbe difféerente
        int[] secret         = new int[nbrBytes];        // Va stocker les secrets f1(0), f2(0), f3(0), f(4)
        nbrParts ++; // On veut la part 0 avec tous les secrets, et de 1 à nbrParts ce seront les Users

        // Faire 32 (nbrBytes) fois le shamir secret
        for (int i=0; i < nbrBytes ;i++)
        {
            //@TODO : Effacer les LOGS, rien ne doit apparaitre !
            polynomes[i] = new Polynome(nbrParts, threshold);

            //1) generate coefficients & xCoordinates to evalue
            polynomes[i].generateCoefficient();
                        //System.out.println("Coefficients : ");
                        //polynomes[i].afficheTab(polynomes[i].getCoefficients());

            polynomes[i].xGenerator();
                        //System.out.println("X Coordinates : ");
                        //polynomes[i].afficheTab(polynomes[i].getxCoordinates());


            //2) crééer courbe / polynome
            //   resoudre pour x=0, x=1--> User 1, X=2--> 2 ....etc....
            polynomes[i].generateParts();
                        //System.out.println("Y Coordinates : ");
                        //polynomes[i].afficheTab(polynomes[i].getyCoordinates());

            //4 stocker resultat x & y de tous dans tableau hors de cette boucle
            // position[1] = user1
            // position[2] = user2
            secret[i] = polynomes[i].getSecret();
            //System.out.println("Secret [i="+i+"] = "+secret[i]);

            System.out.println();
            for (int j = 0; j < nbrParts; j++)
            {
                System.out.println("X : " + polynomes[i].getxCoordinates()[j] +
                        " | Y : " + polynomes[i].getyCoordinates()[j]);
            }
            System.out.println();

            // Pour chaques users, lui donner le résultat du premier shamir, puis du suivant, jusqu'aux 32 shamirs coordinates
            // On donne shamir 1 à tout le monde, puis shamir 2, puis 3 (Donc dans la boucle dans laquelle on est DEJA)
            // Donc il faut une boucle pour passer sur tous les différents users

        }
        // TODO : CACHER CET AFFICHAGE
        System.out.println("Secrets are : ");
        polynomes[0].afficheTab(secret);
        System.out.println();

        System.out.println("Start of serialization, please wait...");
        writePartsToUsersInJSon(polynomes, nbrParts, nbrBytes);
        System.out.println("Serialization done !");
        System.out.println();
    }

    /**
     * Verifys the input set by the user, if ther is an error, we throw an exeption
     * @param nbrBytes should be : 16 / 24 / 32
     * @param nbrParts Any value
     * @param threshold Lesser than nbrParts
     */
    private void verifyInputs(int nbrBytes, int nbrParts, int threshold) throws IllegalArgumentException
    {
        //VERIFIER QUE NBYTE SOIT 16, 24 ou 32 --> Selon le cypher du crypteur
        if(nbrBytes != BYTESVALUE_16 &&
                nbrBytes != BYTESVALUE_24 &&
                nbrBytes != BYTESVALUE_32)
        {
            String message = "GenerateParts Method error : Invalid input --> nbrBytes should be 16,24 or 32";
            System.err.println(message);
            throw new IllegalArgumentException(message);
        }
        if (nbrParts <= threshold)
        {
            String message = "GenerateParts Method error : Invalid input --> threshold should be smaller than number of parts wanted";
            System.err.println(message);
            throw new IllegalArgumentException(message);
        }
        if(nbrParts < 2 || nbrParts > MAX_VALUE_INPUT)
        {
            String message = "GenerateParts Method error : Invalid input --> Number of parts must be at least 2 and maximum of 2'000'000";
            System.err.println(message);
            throw new IllegalArgumentException(message);
        }
        if (threshold < 1 || threshold > MAX_VALUE_INPUT)
        {
            String message = "GenerateParts Method error : Invalid input --> Threshold can't be 0";
            System.err.println(message);
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Writes the generated parts from the polynome class into a JSON file
     * @param polynomes
     * @param nbrParts
     * @param nbrBytes
     */
    private void writePartsToUsersInJSon(Polynome[] polynomes, int nbrParts, int nbrBytes)
    {
        UserParts users[] = new UserParts[nbrParts]; // Crée un tableau contentant chauque paire de points pour notre User

        // Boucler sur tous les users
        for (int userIndex = 1; userIndex < nbrParts; userIndex++) // On commence à 1, car X=0 est le secret !
        {

            Point[] pairsForUser = new Point[nbrBytes]; // Tableau servant d'intermédiaire

            // Boucler sur tous les shamir pour chaque User
            for (int polyIndex = 0; polyIndex < nbrBytes; polyIndex++)
            {
                Point pair = new Point();
                // Prendre la coordonnée X=user du polynome shamir Index
                pair.x = polynomes[polyIndex].getxCoordinates()[userIndex];
                pair.y = polynomes[polyIndex].getyCoordinates()[userIndex];
                //System.out.println(pair.x);
                //System.out.println(pair.y);

                pairsForUser[polyIndex] = new Point();
                pairsForUser[polyIndex].x = polynomes[polyIndex].getxCoordinates()[userIndex];
                pairsForUser[polyIndex].y = polynomes[polyIndex].getyCoordinates()[userIndex];

            }

            // On a terminé de setter tous les X&Y de 1 User,
            // on va maintenant donner le tableau de POINTS intermédiaire à USERPARTS
            // pour pouvoir les WRITE / Serialiser
            users[userIndex] = new UserParts(nbrBytes); // nbrBytes lui dira combien de paire de points il aura au total
            users[userIndex].setPartsByUser(pairsForUser);

            // Maintenant que tous les users de la Classe UserParts ont leur coordonnées,
            // on peut write/serialiser chaque user dans un fichier JSON
            JsonPartsFiles json = new JsonPartsFiles();
            String fileName = Integer.toString(userIndex);
            json.write(users[userIndex], "User_"+fileName);
        }
    }

    public static void main(String[] args)
    {

        System.out.println("START");
        GenerateParts gp = new GenerateParts(16,10 , 4);
        System.out.println("END");
    }

    /**
     * TENTATIVE DE CRéER UN SEULE FOIS LE SCHAMIR SECRET POUR PLUSIEURS USERS
     * @param nbrBytes
     * @param nbrParts
     * @param threshold
     */
    public void GenerateParts_SINGLE(int nbrBytes, int nbrParts, int threshold)
    {
        //MAJ 26.11
        // On va stocker 32 fois notre secret de Y dans un tableau de int[],
        // nbrBytes sera le nombre de fois que on fait shamir secret, et que on stocke le secret.
        // On en aura besoin de 16 ou 32 pour le cryptage

        Polynome polynome = new Polynome(nbrParts, threshold); // Stockera x & y.
        //byte[] secret = new byte[nbrBytes]; //va stocker les secrets f1(0)
        int[] secret = new int[nbrBytes];


        //1) generate coefficients & xCoordinates to evalue
        polynome.generateCoefficient();
        System.out.println("Coefficients : ");
        polynome.afficheTab(polynome.getCoefficients());

        polynome.xGenerator();
        System.out.println("X Coordinates : ");
        polynome.afficheTab(polynome.getxCoordinates());

        //2) crééer courbe / polynome
        //3) resoudre pour x=0, x=1--> User 1, X=2--> 2 ....etc....
        polynome.generateParts();
        System.out.println("Y Coordinates : ");
        polynome.afficheTab(polynome.getyCoordinates());

        //4 stocker resultat x & y de tous dans tableau hors de cette boucle
        // position[1] = user1
        // position[2] = user2
        System.out.println();
        for (int j = 0; j < nbrParts; j++)
        {
            System.out.println("X : " + polynome.getxCoordinates()[j] +
                    " | Y : " + polynome.getyCoordinates()[j]);
        }
        System.out.println();

        // @Todo : Faire ce processus nbrbytes de fois, et stocker dans tableau de int[] mes secrets y0
        // @Todo : Et en plus de stocker le secret, recuperer les coordonnées X & Y dans un tableau de POINTS pour les donner à la sérialisation

        // write all 32 informations of Y in files for Users
        //{
        // X[()pour user 1, sera touours 1];
        // Y[f1(x)= 4, f2(x)=23];
        //}

    }

}
