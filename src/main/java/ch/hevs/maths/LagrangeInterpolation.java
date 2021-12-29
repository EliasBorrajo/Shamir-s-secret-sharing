package ch.hevs.maths;

import java.awt.*;

public class LagrangeInterpolation {
    //private ModularArithmetic ma;

    /*public LagrangeInterpolation()
    {
        //ma = new ModularArithmetic();
    }*/

    public int lagrange(Point[] f, int n)
    {
        int result = 0; // Initialize result

        for (int i = 0; i < n-1; i++)
        {
            // Compute individual terms of above formula
            int term = f[i].y;
            for (int j = 0; j < n-1; j++) {
                if (j != i) {
                    term = ModularArithmetic.division(ModularArithmetic.multiplication(term, f[j].x), // TODO: 23.12.2021 checker que les calculs marchent en modulo
                            ModularArithmetic.soustraction(f[i].x, f[j].x));
                            //term = term * f[j].x / (f[i].x - f[j].x);
                }
            }

            // Add current term to result
            result = ModularArithmetic.addition(result, term);
            //result += term;
        }

        return (int) Math.round(result);

    }
}
