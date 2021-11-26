package ch.hevs.Maths;

import java.awt.*;
import java.security.SecureRandom;

public class Polynome
{
    private final static int MODULO = ModularArithmetic.getMODULO();

    private int[] coefficients;
    private int[] xCoordinates; // REMPLACER PAR CLASSE POINT
    private int[] yCoordinates;
    private int[] fonction; // La courbe avec ses coefficients --> pas utile
    private int secret;

    private int degree;

    public Polynome(int nbParts, int threshold)
    {
        degree = threshold - 1;
        coefficients = new int[degree];
        xCoordinates = new int[nbParts];
        yCoordinates = new int[nbParts];
    }

    public void generateCoefficient()
    {
        // @TODO secureRandom()
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < coefficients.length; i++)
        {
            coefficients[i] =  random.nextInt(MODULO);
        }

        secret = coefficients[0];
        System.out.println("SECRET Y -->coefficient = "+ secret);
    }

    /**
     *generate the x value of the secrete used to evaluate fi(x)
     */
    public void xGenerator()
    {
        /**
         * Génerer les facteurs X, dans un ordre précis.
         * X = 0 --> Secret
         * X = 1 sera pour User 1.
         * X = 2 --> User2
         * X = 3 --> User3
         */
        for (int i = 0; i < xCoordinates.length; i++)
        {
            xCoordinates[i] = i;
        }

        /* RANDOM
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < xCoordinates.length; i++)
        {
            xCoordinates[i] = random.nextInt(MODULO);
        }*/
    }

    /**
     * Methode pour créer la fonction y = f(x)
     * DONC POUR AVOIR CHAQUE ELEMENT DE LA COURBE --> PAS UTILE ?!
     */
    public void generatePolynome()
    {
         fonction = new int[coefficients.length];
        // @TODO génère les valeurs de x et f(x) pour un polynome
        for (int i = 0; i < coefficients.length; i++)
        {
           // fonction = fonction
            
        }
    }

    public void generateParts()
    {
        // Pour chaque coordonée X, il y aura un résultat Y
        for (int i = 0; i < xCoordinates.length; i++)
        {
            yCoordinates[i] = calculatePolynomial(xCoordinates[i]);
        }
        System.out.println();
        System.out.println("SECRET Y -->calculate = "+ yCoordinates[0]);
        System.out.println();
    }

    /**
     * Calcul du polynomial de Y pour X.
     * Le retour de la fonction, doit sauvegarder le Y
     * @param xCoordinate
     */
    public int calculatePolynomial(int xCoordinate )
    {
        int resultY = -1;
        // Calcul du polynomial entier en Y pour X.
        for (int i = 0; i < coefficients.length; i++)
        {
            //result = result + (coef[i]*x^i)
            // !!! 0 power 0 ne fonctionne pas !!!
            if (i == 0)
            {
                resultY = ModularArithmetic.addition(resultY, coefficients[i]); //resultY = resultY + coefficients[i];
            }
            else {
                resultY = ModularArithmetic.addition(resultY,
                            ModularArithmetic.multiplication(coefficients[i],
                                        ModularArithmetic.power(xCoordinate, i ) ));//result = result + (coef[i]*x^i)
            }
        }


        return resultY; // Ce sera notre valeur Y de retour !
    }

    public static void afficheTab(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]+", ");
        }
        System.out.println();
    }

    //*****************************************************
    // G E T T E R S
    //*****************************************************
    public int[] getxCoordinates()
    {
        return xCoordinates;
    }

    public int[] getyCoordinates()
    {
        return yCoordinates;
    }

    public int[] getCoefficients()
    {
        return coefficients;
    }
}
