package generateParts;

import ch.hevs.tools.generateParts.GenerateParts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateTest {
    
    @Test
    public boolean scenarios(boolean flag){

        int nbrBytes=32;
        int nbrParts=10;
        int threshold=3;

        int[] userInputs = {nbrBytes, nbrParts, threshold};


        if (nbrBytes==16 || nbrBytes==24 || nbrBytes==32 && threshold>=2 && nbrParts>=2)
        {
            GenerateParts generateParts = new GenerateParts(32,10,4);
            flag = true;
        }
        else{
            flag = false;
        }

        assertEquals(true, scenarios(flag));

        assertEquals( true, new GenerateParts(32, 10,5));
        assertEquals( true, new GenerateParts(32, 10,5));

        return flag;

    }

    public static void main(String[] args)
    {
        System.out.println("START");
        GenerateParts gp = new GenerateParts(32,10 , 4);
        System.out.println("END");
    }
}
