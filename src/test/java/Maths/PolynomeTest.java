package Maths;

import org.junit.jupiter.api.Test;

public class PolynomeTest {

    private byte secret;
    private int threshold;
    private int numShares;
    private int[] coefficients;

    public void setUp()throws Exception{
        secret =42;
        threshold = 4;
        numShares = 200;
    }

    @Test
    public void createPolynomial() throws Exception{
       assert coefficients.length==threshold;
       //A suivre....
    }

    @Test
    public void createShares() throws Exception{
       //assert share.length == numShares;
        // A suivre...
    }
}
