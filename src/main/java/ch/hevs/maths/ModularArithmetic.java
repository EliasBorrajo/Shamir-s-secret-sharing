package ch.hevs.maths;

import java.math.BigInteger;

/** FRENCH
 * C'est la classe qui nous permettra de faire des calculs en utilisant des opérations (methodes) tel que :
 * ( + ) / ( - ) / ( * ) / ( / )
 *
 * METHODES :
 *
 * - EUCLIDE ETENDU
 *
 * - INVERSION MODULAIRE
 *
 * - INTERPOLATION POLYNOMINALE --> Reconstitution des parts de secrets
 *
 * - GENERER PARTS DE SECRET
 * - Génerer aléatoirement a/b/c (de ax^2 + bx + c = y) avec paramètre X donné, et retourne y
 * @author Elias Borrajo
 */
/** ENGLISH
 * This is the class that will allow us to perform calculations using operations (methods) such as :
 * ( + ) / ( - ) / ( * ) / ( / )
 *
 * METHODS :
 *
 * - EUCLIDE ETENDU
 *
 * - MODULAR INVERSION
 *
 * - POLYNOMINAL INTERPOLATION --> Reconstitution of secret shares
 *
 * - GENERATE SECRET PARTS
 * - Randomly generate a/b/c (from ax^2 + bx + c = y) with given parameter X, and return y
 * @author Elias Borrajo
 */
public abstract class ModularArithmetic
{
    //*****************************************************************************
    // A T T R I B U T E S
    //*****************************************************************************
    private final static int MODULO = 257; // Le modulo à 257 permet de travailler en int sans souci.

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    // ON A TOUJOURS 2 METHODES SIMILAIRES.
    // 1) UTILISANT LA cONSTANTE MODULO = 257 --> CELLE QUE NOUS ON UTILISERA POUR LE PROJET
    // 2) UTILISANT LE MODULO DONNE EN PARAMETRE

    //*****************************************************
    // A D D I T I O N
    //*****************************************************
    public static int addition(int a, int b)
    {

        int result = Math.floorMod((a + b), MODULO);
        return result;
    }

    public static int additionMOD(int a, int b, int modulo)
    {

        int result = Math.floorMod((a + b), modulo);
        return result;
    }

    //*****************************************************
    // S U B S T R A C T I O N
    //*****************************************************
    public static int subtraction(int a, int b)
    {

        int result = Math.floorMod((a - b), MODULO);

        return result;
    }

    public static int subtractionMOD(int a, int b, int modulo)
    {

        int result = Math.floorMod((a - b), modulo);

        return result;
    }

    //*****************************************************
    // M U L T I P L I C A T I O N
    //*****************************************************
    public static int multiplication(int a, int b)
    {
        int result = Math.floorMod((a * b), MODULO);

        return result;
    }

    public static int multiplicationMOD(int a, int b, int modulo)
    {
        int result = Math.floorMod((a * b), modulo);

        return result;
    }

    //*****************************************************
    // D I V I S I O N --> USE ModInverse
    //*****************************************************
    public static int division(int a, int b)
    {
        if (b == 0)
        {
            throw new ArithmeticException("In division, can't divide by 0");
        }

        return multiplication(a, modularInverseEEA_MOD(b, MODULO)); //a * inv multi de B
    }

    public static int divisionMOD(int a, int b, int modulo)
    {
        if (b == 0)
        {
            throw new ArithmeticException("In division, can't divide by 0");
        }

        return multiplication(a, modularInverseEEA_MOD(b, modulo)); // a * inv multi de B
    }

    //*****************************************************
    // P U I S S A N C E
    //*****************************************************
    public static int power(int value, int exponent)
    {
        if (exponent <= 0)
        {
            throw new ArithmeticException("Power can not be <= 0. It should be power >= 1 minimum");
        }

        int result = 1;
        for (int i = 0; i < exponent; i++)
        {
            result = multiplication(result, value); // result = result * value
        }
        return result;
    }

    public static int powerMOD(int value, int exponent, int modulo)
    {
        if (exponent <= 0)
        {
            throw new ArithmeticException("Power can not be <= 0. It should be power >= 1 minimum");
        }

        int result = 1;
        for (int i = 0; i < exponent; i++)
        {
            result = multiplicationMOD(result, value, modulo); // result = result * value
        }
        return result;
    }


    //*****************************************************
    // G R E A T E S T   C O M M O N   D I V I S O R
    //*****************************************************
    /**
     * https://www.baeldung.com/java-greatest-common-divisor
     * Euclid's algorithm is not only efficient but also easy to understand and easy to implement using recursion in Java.
     * Euclid's method depends on two important theorems:
     *
     * First, if we subtract the smaller number from the larger number, the GCD doesn't change – therefore, if we keep on subtracting the number we finally end up with their GCD
     * Second, when the smaller number exactly divides the larger number, the smaller number is the GCD of the two given numbers.
     * Note in our implementation that we'll use modulo instead of subtraction since it's basically many subtractions at a time:
     * Also, note how we use n2 in n1‘s position and use the remainder in n2's position in the recursive step of the algorithm.
     *
     * Further, the complexity of Euclid's algorithm is O(Log min(n1, n2)) which is better as compared to the Brute Force method.
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b)
    {
        // Finitude
        if (b == 0)
        {
            return a;
        }

        //Recursion
        return gcd(b, a % b);
    }

    public static boolean isPrime(int value)
    {
        int reste;
        boolean flag = true; // Returne True if value is a Prime-Number

        for (int i = 2; i <= value / 2; i++)
        {
            //nombre est divisible par lui-meme
            reste = value % i;

            //si le reste est 0, alors arrete la boucle. Sinon continuer la boucle
            if (reste == 0)
            {
                flag = false;
                break;
            }
        }
        //si flag est true, alors nombre est premier, sinon non premier
        if (flag)
        {
            //System.out.println("Modulo = " +value + " : Is a prime number");
        }

        else
            System.out.println("Modulo = " +value + " : Is not a prime number");


        return flag;

    }

    //*****************************************************
    // M O D U L A R   I N V E R S E
    //*****************************************************
    /**
     * Multiplicative inverse of A modulo M
     * EEA = Extended Euclidean Algorithm
     *  1) Greatest common divisor GCD of value(a) & modulo(b). value >= modulo
     * 2) Coefficient of bezout set of Z : ax + by = g = gcd
     * 3) For modulo prime number & value(a) set of Z modulo base, replace in the equation a by m : mx + by = 1 --> return y = multiplicative inverse.
     * @param value : Number for which we want the modular inverse
     * @param modulo : Must be a prime number greater or equal than the given value
     * @return : the modular inverse of the value
     */
    public static int modularInverseEEA_MOD(int value, int modulo)
    {
        // Verifier les entrées
        if(!isPrime(modulo))
            throw new ArithmeticException("ModularInverseEEA : Modulo param is not a prime number !");

        if (modulo <= 0)
            throw new ArithmeticException("ModularInverseEEA : Modulo param is <= 0");

        if (gcd(value, modulo) != 1)
            throw new ArithmeticException("ModularInverseEEA : Modulo & Value are not Co-Prime");

        int r0 = modulo;
        int r1 = Math.floorMod(value, modulo); // 0 <= r1 < m
        int y0 = 0;
        int y1 = 1;

        if (r1 == 0)
        {
            throw new ArithmeticException("Zero has no multiplicative inverse");
        }

        while (r1 > 0)
        {
            int[] qr = new int[2];  //quotient & rest  //r0.divideAndRemainder(r1); --> Avec BigInteger

            qr[0] = r0 / r1;
            //qr[1] = r0 - r1 * qr[0];  //soustraction(r0, (multiplication(r1,qr[0])) ) ;

            //qr[0] = divisionMOD(r0,r1,mod);
            qr[1] = subtractionMOD(r0, (multiplicationMOD(r1,qr[0], modulo)), modulo ) ;


            r0 = r1;
            r1 = qr[1];

            int temp = multiplicationMOD(y1, qr[0], modulo);
            int newY1 = subtractionMOD(y0, temp, modulo);
            y0 = y1;
            y1 = newY1;
        /*
            BigInteger[] qr = r0.divideAndRemainder(r1); // qr[0] = r0/r1 and qr[1] = r0 - r1*qr[0]
            r0 = r1;
            r1 = qr[1];

            BigInteger newY1 = y0.subtract(y1.multiply(qr[0]));
            y0 = y1;
            y1 = newY1;
        */
        }

        if (!(r0 == 1))
        {
            throw new ArithmeticException("a is not relatively prime to m");
        }

        return Math.floorMod(y0, modulo);


        // 1) Trouver gcd(a,b) --> nombre premier
        // 2) Coefficient de Bézout
        // 3) Modulo m --> nombre premier a Ensemble Zm
        //    y = inverse multiplicatif

    }

    public static int modularInverseEEA(int value)
    {
        // Verifier les entrées
        if(!isPrime(MODULO))
            throw new ArithmeticException("ModularInverseEEA : Modulo param is not a prime number !");

        if (MODULO <= 0)
            throw new ArithmeticException("ModularInverseEEA : Modulo param is <= 0");

        if (gcd(value, MODULO) != 1)
            throw new ArithmeticException("ModularInverseEEA : Modulo & Value are not Co-Prime");

        int r0 = MODULO;
        int r1 = Math.floorMod(value, MODULO); // 0 <= r1 < m
        int y0 = 0;
        int y1 = 1;

        if (r1 == 0)
        {
            throw new ArithmeticException("Zero has no multiplicative inverse");
        }

        while (r1 > 0)
        {
            int[] qr = new int[2];  //quotient & rest  //r0.divideAndRemainder(r1); --> Avec BigInteger

            qr[0] = r0 / r1;
            qr[1] = subtraction(r0, (multiplication(r1,qr[0])) ) ;


            r0 = r1;
            r1 = qr[1];

            int temp = multiplication(y1, qr[0]);
            int newY1 = subtraction(y0, temp);
            y0 = y1;
            y1 = newY1;
        }

        if (!(r0 == 1))
        {
            throw new ArithmeticException("a is not relatively prime to m");
        }

        return Math.floorMod(y0, MODULO);

    }

    // Multiplicative inverse of A modulo M
    public static BigInteger modInverseEEA_BigInteger(BigInteger a, BigInteger m) throws ArithmeticException
    {

        if (m.compareTo(BigInteger.ZERO) <= 0)
        {
            throw new ArithmeticException("Modulus <= 0");
        }

        BigInteger r0 = m;
        BigInteger r1 = a.mod(m); // 0 <= r1 < m
        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;

        if (r1.equals(BigInteger.ZERO))
        {
            throw new ArithmeticException("Zero has no multiplicative inverse");
        }

        while (r1.compareTo(BigInteger.ZERO) > 0)
        {
            BigInteger[] qr = r0.divideAndRemainder(r1); // qr[0] = r0/r1 and qr[1] = r0 - r1*qr[0]
            r0 = r1;
            r1 = qr[1];

            BigInteger newY1 = y0.subtract(y1.multiply(qr[0]));
            y0 = y1;
            y1 = newY1;
        }

        if (!r0.equals(BigInteger.ONE))
        {
            throw new ArithmeticException("a is not relatively prime to m");
        }

        return y0.mod(m);
    }


    // Iterative Java program to find modular
    // inverse using extended Euclid algorithm
    // Returns modulo inverse of A with
    // respect to M using extended Euclid
    // Algorithm Assumption: a and m are
    // coprimes, i.e., gcd(a, m) = 1
    static int modInverse_web(int a, int m)
    {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1)
        {
            // q is quotient
            int q = a / m;

            int t = m;

            // m is remainder now, process
            // same as Euclid's algo
            m = a % m;
            a = t;
            t = y;

            // Update x and y
            y = x - q * y;
            x = t;
        }

        // Make x positive
        if (x < 0)
            x += m0;

        return x;
    }

    //*****************************************************************************
    // G E T T E R S / S E T T E R S
    //*****************************************************************************
    public static int getMODULO()
    {
        return MODULO;
    }

    public static void main(String[] args)
    {
        int a = 899000979;
        int mod = 659;
        BigInteger A = new BigInteger(Integer.toString(a));
        BigInteger MOD = new BigInteger(Integer.toString(mod));

        System.out.println(modInverseEEA_BigInteger(A, MOD));
        System.out.println(modularInverseEEA_MOD(a, mod));
        System.out.println(modInverse_web(a, mod));
/*
        System.out.println("POWER OF... TEST");
        System.out.println(power(2, 4));
        System.out.println(power(2, 8));
        System.out.println(power(2, 16));
        System.out.println(power(2, 32));


        /*
        System.out.println("Modular Arithmetic Test");

        System.out.println("ADDITION");
        System.out.print("a) ");
        MODULO = 5;
        int a = addition(20,8);
        System.out.println("Result = "+a);//--> OK !

        System.out.println("MULTIPLICATION");
        System.out.print("b) ");
        MODULO = 10;
        int b = multiplication(3,7);
        System.out.println("Result = "+b);//--> OK !

        System.out.print("c) ");
        MODULO = 19;
        int c = multiplication(10,10);
        System.out.println("Result = "+c);  //--> OK !


        System.out.print("d) ");
        MODULO =    (int) (Math.pow(2,255))-19;

        int value = (int) (Math.pow(2,255));
        int d = soustraction(value,13); //2^255  -13 mod 2^255  -19
        System.out.println("Result = "+d);  //--> OK !

        System.out.println("GCD test");
        System.out.println("GCD is = "+ gcd(59,12) );    //--> OK
        System.out.println("GCD is = "+ gcd(3000,256) ); //--> OK

         */

    }


}
