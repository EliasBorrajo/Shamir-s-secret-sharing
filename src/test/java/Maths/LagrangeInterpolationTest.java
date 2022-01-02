package Maths;

import ch.hevs.maths.LagrangeInterpolation;
import org.junit.jupiter.api.Test;

import java.awt.*;


public class LagrangeInterpolationTest {

    @Test
    public static void lagrange(Point[] f, int i)
    {
        Point[] arr = new Point[5];
        int n = LagrangeInterpolation.lagrange(arr,2);
        assert(n)==(2);

        //cas limite dépasser le int ou valeur négative

    }

    public void lagrange2()
    {
        Point[] arr = new Point[5];
        int n = LagrangeInterpolation.lagrange(arr,-2);
        assert(n)==(-2);
    }

    public static void main(String[] args)
    {
         //array of known points
        Point f[] = {new Point(2, 1942), new Point(4, 3402),
                new Point(5, 4414)};
        // Using the interpolate function to obtain
        // a point corresponding to x=3
        //System.out.print("Value of f(0) is : " + lagrange(f, 4));
        System.out.println();

        byte b = (byte) 127;
        byte b1 = (byte) 254;
        byte b2 = (byte) 256;
        byte b3 = (byte) 257;
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }
}
