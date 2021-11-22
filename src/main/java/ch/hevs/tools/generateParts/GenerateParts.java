package ch.hevs.tools.generateParts;

/**
 * 1) Génerer les parts de secrets et en obtenir un fichier.
 * - Entrées   : #Bytes (modulo) / #Parts (10 employés) / Seuil (k  <= # Parts)
 * - Sortie    : #Fichier en fonction de #Parts. Fichier JSON contenant les parts de secrets
 * - Process   : Le degrée du polynome sera = seuil k-1, et nous génerera les parts a0, a1,..., an-1
 * Puis on évalue f(x) = ax^2 + bx +c (dépend du degré) --> pour X
 */

import ch.hevs.Maths.Polynome;

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


    /*public GenerateParts(int nbrBytes, int nbrParts, int threshold)
    {
        //tableau de polynomes, il va stocker nos coordonées
        Polynome[] polynomes= new Polynome[nbrParts]; // Stockera x & y.| Chaque index --> une courbe difféerente
        byte[] secret     = new byte[nbrBytes]; //va stocker les secrets f1(0), f2(0), f3(0), f(4)


        /**
         * Faire 32 fois le shamir secret OU Nbrparts total shamir ??
         */
    /*    for (int i=0; i < nbrParts ;i++)
        {
            //1) generate coefficients & xCoordinates to evalue
            polynomes[i].generateCoefficient();
            polynomes[i].xGenerator();

            //2) crééer courbe / polynome
            //3) resoudre pour x=0, x=1--> User 1, X=2--> 2 ....etc....
            polynomes[i].generateParts();
           /* for (i<user)
            {
                //4 stocker resultat x & y de tous dans tableau hors de cette boucle
                // position[1] = user1
                // position[2] = user2
            }*/
/*
            for (int j = 0; j < nbrParts; j++)
            {
                System.out.println("X : "+ polynomes[i].getxCoordinates()+
                                   "Y : "+ polynomes[i].getyCoordinates() );
            }

        }



        // write all 32 informations of Y in files for Users
        //{
        // X[()pour user 1, sera touours 1];
        // Y[f1(x)= 4, f2(x)=23];
        //}

    }*/

    /**
     * TENTATIVE DE CRéER UN SEULE FOIS LE SCHAMIR SECRET POUR PLUSIEURS USERS
     * @param nbrBytes
     * @param nbrParts
     * @param threshold
     */
    public GenerateParts(int nbrBytes, int nbrParts, int threshold)
    {

        Polynome polynome = new Polynome(nbrParts, threshold); // Stockera x & y.
        byte[] secret = new byte[nbrBytes]; //va stocker les secrets f1(0)


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
