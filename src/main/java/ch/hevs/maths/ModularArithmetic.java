package ch.hevs.Maths;

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
    private final static int MODULO = 257;
    
    public int addition(int a, int b)
    {
        //@todo If a positif & b négatif, toujours faire floorMod et pas divMod ? ?

        int result = Math.floorMod((a+b), MODULO);
        
        return result;
    }

    public int soustraction(int a, int b)
    {
        //@todo If a positif & b négatif, toujours faire floorMod et pas divMod ? ?

        int result = Math.floorMod((a-b), MODULO);

        return result;
    }

    public int power(int value, int exponent)
    {

    }

    public int multiplication(int a, int b)
    {
        int result = Math.floorMod((a * b), MODULO);

        return result;
    }

   /* public int division(int a, int b)
    {

    }

    public int modularInverse()
    {

    }*/

    public static void main(String[] args)
    {

    }




}
