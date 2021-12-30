package ch.hevs.maths;

import java.security.SecureRandom;
/**
 * TODO Trouver texte
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
     * Calcul du polynomial de Y pour X.
     * Le retour de la fonction, doit sauvegarder le Y
     * @param xCoordinate
     */
    public int calculatePolynomial_OLD_DOESNOTWORKPROPERLY(int xCoordinate )
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
