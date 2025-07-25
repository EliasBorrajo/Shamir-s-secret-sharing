package ch.hevs.maths;

import java.awt.*;

/**
 * Class used to perform a Lagrange interpolation on an array of points
 * @authors : Milena Lonfat, Emilie Teodoro, Jonathan Bourquin
 */
public class LagrangeInterpolation
{
    //private ModularArithmetic ma;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************

    /**
     * constructor
     */
    public LagrangeInterpolation()
    {
        //ma = new ModularArithmetic();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************

    /**
     * Lagrange interpolation algorithm
     * @param points array of type Point
     * @param kThreshold threshold
     * @return
     */
    public int lagrange(Point[] points, int kThreshold)
    {
        int result = 0; // Initialize result

        for (int i = 0; i < kThreshold - 1; i++) // i = f(x) = SUM y*Li(x)
        {
            // Compute individual terms of above formula
            int term = points[i].y;

            for (int j = 0; j < kThreshold - 1; j++) // j = li(x) = MULTI (x-xm)/(xi-xm)
            {
                if (j != i)
                {
                    term = ModularArithmetic.division(ModularArithmetic.multiplication(term, points[j].x),
                            ModularArithmetic.subtraction(points[i].x, points[j].x));

                    //term = term * f[j].x / (f[i].x - f[j].x);
                }
            }

            // Add current term to result

            result = ModularArithmetic.addition(result, term);
            //result += term;
        }

        return (int) Math.round(result);

    }

    public static void main(String[] args)
    {
        // array of known points
        /*Point f[] = {new Point(2, 1942), new Point(4, 3402),
                new Point(5, 4414)};
        // Using the interpolate function to obtain
        // a point corresponding to x=3
        System.out.print("Value of f(0) is : " + lagrange(f, 4));
        System.out.println();

        byte b = (byte) 127;
        byte b1 = (byte) 254;
        byte b2 = (byte) 256;
        byte b3 = (byte) 257;
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);*/
    }

}
