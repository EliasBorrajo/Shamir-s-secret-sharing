package Maths;

import ch.hevs.maths.LagrangeInterpolation;
import org.junit.jupiter.api.Test;

import java.awt.*;


public class LagrangeInterpolationTest {

    @Test
    public void lagrange()
    {
        Point[] arr = new Point[5];
        int n = LagrangeInterpolation.lagrange(arr,2);
        assert(n)==(2);

        //cas limite dépasser le int ou valeur négative

    }

    public void lagrange2()
    {
        Point[] arr = new Point[5];
        int n = LagrangeInterpolation.lagrange(arr,-1);
        assert(n)==(-1);
    }
}
