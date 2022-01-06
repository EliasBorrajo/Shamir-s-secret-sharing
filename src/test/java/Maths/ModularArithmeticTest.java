package Maths;

import ch.hevs.maths.ModularArithmetic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModularArithmeticTest {

    @Test
    public void addition()
    {
       assertEquals(7, ModularArithmetic.addition(5,2));

       assertEquals(7, ModularArithmetic.additionMOD(5,2,8));
       assertEquals(0, ModularArithmetic.additionMOD(5,3,8));
       assertEquals(1, ModularArithmetic.additionMOD(5,4,8));
       assertEquals(2, ModularArithmetic.additionMOD(5,5,8));

    }

    @Test
    public void soustraction(){
        assertEquals(3, ModularArithmetic.addition(5,2));

        assertEquals(3, ModularArithmetic.subtractionMOD(5,2,7));
        assertEquals(1, ModularArithmetic.subtractionMOD(5,4,7));
        assertEquals(1, ModularArithmetic.subtractionMOD(10,2,7));
        assertEquals(2, ModularArithmetic.subtractionMOD(10,1,7));

    }

    @Test
    public void multiplication(){
        assertEquals(10, ModularArithmetic.multiplication(5,2));

        assertEquals(3, ModularArithmetic.multiplicationMOD(5,2,7));
        assertEquals(6, ModularArithmetic.multiplicationMOD(5,4,7));
        assertEquals(6, ModularArithmetic.multiplicationMOD(10,2,7));
        assertEquals(3, ModularArithmetic.multiplicationMOD(10,1,7));

    }

    @Test
    public void division(){
        assertThrows(ArithmeticException.class, () -> ModularArithmetic.division(5,2));

        assertEquals(5, ModularArithmetic.division(10,2));

        assertEquals(2,ModularArithmetic.divisionMOD(5,2,7));
        assertEquals(3, ModularArithmetic.divisionMOD(12,4,7));
        assertEquals(5, ModularArithmetic.divisionMOD(10,2,7));
        assertEquals(3, ModularArithmetic.divisionMOD(10,1,7));


    }

    @Test
    public void power(){


        assertEquals(4,ModularArithmetic.powerMOD(5,2,7));
        assertEquals(2, ModularArithmetic.powerMOD(4,2,7));
        assertEquals(2, ModularArithmetic.powerMOD(3,2,7));
        assertEquals(1, ModularArithmetic.powerMOD(6,2,7));

    }

    @Test
    public void gcd(){

        assertEquals(1,ModularArithmetic.gcd(5,2));
        assertEquals(2, ModularArithmetic.gcd(4,2));
        assertEquals(4, ModularArithmetic.gcd(8,4));
        assertEquals(3, ModularArithmetic.gcd(6,3));

    }

    @Test
    public void isPrime(){

        assertEquals(true,ModularArithmetic.isPrime(2));
        assertEquals(true, ModularArithmetic.isPrime(7));
        assertEquals(false, ModularArithmetic.isPrime(8));
        assertEquals(false, ModularArithmetic.isPrime(6));

    }

}
