package ch.hevs.Maths;

import java.awt.*;

public class LagrangeInterpolation {
    private ModularArithmetic ma;

    public LagrangeInterpolation()
    {
        ma = new ModularArithmetic();
    }


    public int lagrange(Point[] f, int n)
    {
        double result = 0; // Initialize result

        for (int i = 0; i < n-1; i++)
        {
            // Compute individual terms of above formula
            double term = f[i].y;
            for (int j = 0; j < n-1; j++) {
                if (j != i) {
                    term = term * f[j].x / (f[i].x - f[j].x);
                }
            }

            // Add current term to result
            result += term;
        }

        return (int) Math.round(result);

    }
    /*public static void main(String[] args)
    {
        // array of known points
        Point f[] = {new Point(2, 1942), new Point(4, 3402),
                new Point(5, 4414)};
        // Using the interpolate function to obtain
        // a point corresponding to x=3
        System.out.print("Value of f(0) is : " + lagrange(f, 4));
        System.out.println();

        *//*byte b = (byte) 128;
        byte b1 = (byte) 254;
        byte b2 = (byte) 256;
        byte b3 = (byte) 257;
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);*//*
    }*/

}
