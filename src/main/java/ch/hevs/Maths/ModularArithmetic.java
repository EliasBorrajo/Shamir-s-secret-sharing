package ch.hevs.Maths;

import java.math.BigInteger;

/**
 * C'est la classe qui nous permettra de faire des calculs en utilisant des opérations (methodes) tel que :
 * ( + ) / ( - ) / ( * ) / ( / )
 *
 * METHODES :
 *
 *  - EUCLIDE ETENDU
 *
 *  - INVERSION MODULAIRE
 *
 *  - INTERPOLATION POLYNOMINALE --> Reconstitution des parts de secrets
 *
 *  - GENERER PARTS DE SECRET
 *        - Génerer aléatoirement a/b/c (de ax^2 + bx + c = y) avec paramètre X donné, et retourne y
 */
public class ModularArithmetic
{
    //*****************************************************
    // A T T R I B U T E S
    //*****************************************************

    private /*final @todo*/ static int MODULO = 257;

    //*****************************************************
    // M E T H O D S
    //*****************************************************
    public static int addition(int a, int b)
    {
        //@todo If a positif & b négatif, toujours faire floorMod et pas divMod ? ?

        int result = Math.floorMod((a+b), MODULO);
        return result;
    }

    public static int soustraction(int a, int b)
    {
        //@todo If a positif & b négatif, toujours faire floorMod et pas divMod ? ?

        int result = Math.floorMod((a-b), MODULO);

        return result;
    }

    public static int power(int value, int exponent)
    {
        if (exponent<=0)
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

    public static int multiplication(int a, int b)
    {
        int result = Math.floorMod((a * b), MODULO);

        return result;
    }

    public static int division(int a, int b, int mod)
    {
        if (b==0)
        {
            throw new ArithmeticException("In division, can't divide by 0");
        }

        return multiplication(a, Math.floorMod(b,mod));
    }

    // Multiplicative inverse of A modulo M
    // EEA = Extended Euclidean Algorithm
    public static int modularInverseEEA(int value, int mod)
    {
        if (mod <= 0)
        {
            throw new ArithmeticException("Modulus <= 0");
        }

        int r0 = mod;               int r1 = Math.floorMod(value,mod); // 0 <= r1 < m
        int y0 = 0;                 int y1 = 1;

        if (r1 == 0)
        {
            throw new ArithmeticException("Zero has no multiplicative inverse");
        }

        while (r1 > 0)
        {
            int[] qr = new int[2];  //r0.divideAndRemainder(r1); --> Avec BigInteger
            //qr[0] = division(r0,r1,mod);
            qr[0] = r0/r1;
            qr[1] = r0 - r1*qr[0];  //soustraction(r0, (multiplication(r1,qr[0])) ) ;
            r0 = r1;
            r1 = qr[1];

            int temp = multiplication(y1,qr[0]);
            int newY1 = soustraction(y0, temp ) ;
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

        if ( !(r0 == 1))
        {
            throw new ArithmeticException("a is not relatively prime to m");
        }

        return Math.floorMod(y0,mod);


        // 1) Trouver gcd(a,b) --> nombre premier
        // 2) Coefficient de Bézout
        // 3) Modulo m --> nombre premier a Ensemble Zm
        //    y = inverse multiplicatif

    }

    // Multiplicative inverse of A modulo M
    public static BigInteger modInverseEEA(BigInteger a, BigInteger m) throws ArithmeticException
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
     * @param a
     * @param b
     * @return
     */
    private static int gcd(int a, int b)
    {
        // Finitude
        if (b == 0)
        {
            return a;
        }

        //Recursion
        return gcd(b, a % b);
    }


        // Iterative Java program to find modular
        // inverse using extended Euclid algorithm
        // Returns modulo inverse of A with
        // respect to M using extended Euclid
        // Algorithm Assumption: a and m are
        // coprimes, i.e., gcd(a, m) = 1
        static int modInverse(int a, int m)
        {
            int m0 = m;
            int y = 0, x = 1;

            if (m == 1)
                return 0;

            while (a > 1) {
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

    public static int getMODULO()
    {
        return MODULO;
    }

    public static void main(String[] args)
    {
        /*int a = 9;
        int mod = 257;
        BigInteger A = new BigInteger( Integer.toString(a));
        BigInteger MOD = new BigInteger( Integer.toString(mod));

        System.out.println(modInverseEEA(A,MOD));
        System.out.println(modularInverseEEA(a, mod));
        System.out.println(modInverse(a,mod));

        System.out.println("POWER OF... TEST");
        System.out.println(power(2,4));
        System.out.println(power(2,8));
        System.out.println(power(2,16));
        System.out.println(power(2,32));*/


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
