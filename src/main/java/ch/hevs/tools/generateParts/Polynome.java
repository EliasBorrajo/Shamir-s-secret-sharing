package ch.hevs.tools.generateParts;

import ch.hevs.maths.ModularArithmetic;

import java.security.SecureRandom;
/** FRENCH
 * Cette classe est le polynome. Il va contenir toutes les informations nécessaire sur chaque shamir que on fait.
 * les coordonées X&Y, les coefficients.
 * Cette classe permet de calculer des points sur la courbbe du polynome,
 * et de retourner ces points afin de les donner plus loin dans le code aux classes de sérialisations.
 * @author Elias Borrajo
 */
/** ENGLISH
 * This class is the polynomial. It will contain all the necessary information about each shamir we do.
 * The X&Y coordinates, the coefficients.
 * This class allows you to calculate points on the polynomial curve,
 * and to return these points in order to give them later in the code to the serialization classes.
 * @author Elias Borrajo
 */
public class Polynome //TODO : Doit aller dans package GENERATEparts
{
    //*****************************************************************************
    // A T T R I B U T E S
    //*****************************************************************************
    private final static int MODULO = ModularArithmetic.getMODULO();

    private int[] coefficients;
    private int[] xCoordinates;
    private int[] yCoordinates;

    private int secret;

    private int degree;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    public Polynome(int nbParts, int threshold)
    {
        degree = threshold - 1;
        coefficients = new int[threshold];         // Coefficients  = threshold
        xCoordinates = new int[nbParts];
        yCoordinates = new int[nbParts];
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * Coefficients doivent être sur 8 bits, e 0 à 255, donc modulo 256.
     * C'est car on devra convertir ces coeff de INT en BYTE, car on en aura besoin pour la classe de cryptage du prof
     * Donc on doit travailler sur 8 bits
     */
    public void generateCoefficient()
    {
        final int MODULO_COEFF = 256;
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < coefficients.length; i++)
        {
            coefficients[i] =  random.nextInt(MODULO_COEFF);
            //System.out.println("Coefficient "+i+" is : "+coefficients[i]);
        }

        secret = coefficients[0];
        //System.out.println("SECRET Y --> Coefficient[0] = "+ secret);
    }

    /**
     * Generate the x value of the secrete used to evaluate fi(x)
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

    }

    public void generateParts()
    {
        // Pour chaque coordonée X, il y aura un résultat Y
        for (int i = 0; i < xCoordinates.length; i++)
        {
            yCoordinates[i] = calculatePolynomial(xCoordinates[i]);
        }
        /*System.out.println();
        System.out.println("SECRET Y --> calculate = "+ yCoordinates[0]);
        System.out.println();*/
    }


    /**
     * Computes the value of the polynomial with the given coefficients
     * at the point x, using Horner's Rule.
     *
     * a0 + a1*x^1 + a2*x^2 + a3*x^3 --> HORNER
     * --> ( (a3 * x + a2) * x + a1 ) * x + a0
     * @param xCoordinate
     * @return
     */
    public int calculatePolynomial(int xCoordinate)
    {   int lastCoeffPos = coefficients.length - 1;
        int coeffAMax = coefficients[ lastCoeffPos ];
        int resultY = coeffAMax;

        // On commence à PosMAX - 1, pour addditionner au premier tour Coeff2 sur coeffmax3
        for (int i = lastCoeffPos -1 ; i >= 0 ; i--)
        {
            //result = (result * x) + coefficients[i]
            resultY = ModularArithmetic.addition(ModularArithmetic.multiplication(resultY, xCoordinate) , coefficients[i] ) ;
        }

        return resultY;
    }

    public static void afficheTab(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]+", ");
        }
        System.out.println();
    }

    //*****************************************************************************
    // G E T T E R S / S E T T E R S
    //*****************************************************************************
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

    public int getSecret()
    {
        return secret;
    }
}
