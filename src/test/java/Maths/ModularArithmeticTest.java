package Maths;

import ch.hevs.maths.ModularArithmetic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModularArithmeticTest {

    @Test
    public void addition()
    {
       //assertThrows(ArithmeticException.class, () -> ModularArithmetic.addition(5,2));
       assertEquals(7, ModularArithmetic.addition(5,2));

       assertEquals(7, ModularArithmetic.additionMOD(5,2,8));
       assertEquals(0, ModularArithmetic.additionMOD(5,3,8));
       assertEquals(1, ModularArithmetic.additionMOD(5,4,8));
       assertEquals(2, ModularArithmetic.additionMOD(5,5,8));

    }

    @Test
    public void soustraction(){
        assertThrows(ArithmeticException.class, () -> ModularArithmetic.soustraction(5,2));
    }

    @Test
    public void multiplication(){
        assertThrows(ArithmeticException.class, () -> ModularArithmetic.multiplication(5,2));
    }

    @Test
    public void division(){
        assertThrows(ArithmeticException.class, () -> ModularArithmetic.division(5,2));
        assertEquals(false, ModularArithmetic.division(5,0));

    }


    @Test
    public void power(){
        assertThrows(ArithmeticException.class, () -> ModularArithmetic.power(5,2));
    }

    @Test
    public void gcd(){ // gcd est private ?
        assertThrows(ArithmeticException.class, () -> ModularArithmetic.gcd(5,2));
    }

    @Test
    public void isPrime(){ // isPrime est private ?
        assertThrows(ArithmeticException.class, () -> ModularArithmetic.isPrime(5));
    }







}
