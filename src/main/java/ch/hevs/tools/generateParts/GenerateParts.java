package ch.hevs.tools.generateParts;

/**
 * 1) Génerer les parts de secrets et en obtenir un fichier.
 * - Entrées   : #Bytes (modulo) / #Parts (10 employés) / Seuil (k  <= # Parts)
 * - Sortie    : #Fichier en fonction de #Parts. Fichier JSON contenant les parts de secrets
 * - Process   : Le degrée du polynome sera = seuil k-1, et nous génerera les parts a0, a1,..., an-1
 * Puis on évalue f(x) = ax^2 + bx +c (dépend du degré) --> pour X
 */

import ch.hevs.Maths.Polynome;

import java.awt.*;
import java.math.BigInteger;
import java.security.SecureRandom;

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
    public GenerateParts(int nbrBytes, int nbrParts, int threshold)
    {
        //VERIFIER QUE NBYTE SOIT 16, 24 ou 32 --> Selon le cypher du crypteur
        if(nbrBytes != 16 && nbrBytes != 24 && nbrBytes != 32)
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


        // tableau de polynomes, il va stocker nos coordonées
        // Taille = nbr de bytes, car nbrBtes = le nombre de fois que on fait shamir secret
        Polynome[] polynomes = new Polynome[nbrBytes];   // Stockera x & y.| Chaque index --> une courbe difféerente
        int[] secret         = new int[nbrBytes];        // Va stocker les secrets f1(0), f2(0), f3(0), f(4)
        nbrParts ++; // On veut la part 0 avec tous les secrets, et de 1 à nbrParts ce seront les Users

        // Faire 32 (nbrBytes) fois le shamir secret
        for (int i=0; i < nbrBytes ;i++)
        {
            //@TODO : Effacer les LOGS, rien ne doit apparaitre
            polynomes[i] = new Polynome(nbrParts, threshold);

            //1) generate coefficients & xCoordinates to evalue
            polynomes[i].generateCoefficient();
            System.out.println("Coefficients : ");
            polynomes[i].afficheTab(polynomes[i].getCoefficients());

            polynomes[i].xGenerator();
            System.out.println("X Coordinates : ");
            polynomes[i].afficheTab(polynomes[i].getxCoordinates());


            //2) crééer courbe / polynome
            //   resoudre pour x=0, x=1--> User 1, X=2--> 2 ....etc....
            polynomes[i].generateParts();
            System.out.println("Y Coordinates : ");
            polynomes[i].afficheTab(polynomes[i].getyCoordinates());

            //4 stocker resultat x & y de tous dans tableau hors de cette boucle
            // position[1] = user1
            // position[2] = user2
            secret[i] = polynomes[i].getSecret();
            System.out.println("Secret [i="+i+"] = "+secret[i]);

            System.out.println();
            for (int j = 0; j < nbrParts; j++)
            {
                System.out.println("X : " + polynomes[i].getxCoordinates()[j] +
                        " | Y : " + polynomes[i].getyCoordinates()[j]);
            }
            System.out.println();


            /*for (i<user)
            {
                //4 stocker resultat x & y de tous dans tableau hors de cette boucle
                // position[1] = user1
                // position[2] = user2
            }*/

        }
        System.out.println("Secrets are : ");
        polynomes[0].afficheTab(secret);



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

    public static void main(String[] args)
    {
        GenerateParts gp = new GenerateParts(32, 8, 4);
    }

}
