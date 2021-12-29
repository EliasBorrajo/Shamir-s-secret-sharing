package generateParts;

import ch.hevs.tools.generateParts.GenerateParts;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateTest {

    //Utilisateur qui rentre mauvaise données
    @Test
    public void scenarios(){
        assertEquals( true, new GenerateParts(32, 10,5));
    }
}
