package ch.hevs.tools.generateParts;

import java.awt.*;
import java.util.Arrays;

/**
 * Class for user parts. Each object will be an instanciate a piece of secret (Point array with the same X coordinate for each point and a specific Y coordinate)
 * that will be exported in a json file
 */
public class UserParts
{
    private Point[] parts;

    public UserParts(int nbBytes)
    {
        parts = new Point[nbBytes]; //@todo selon nb de bytes

        /*for (int i = 0; i < 32; i++)
        {
            parts[i] = new Point(seuil, (int)(Math.random() * 100));
             // testing with random values for user 2 (X = 2)
        }*/

    }

    @Override
    public String toString()
    {
        return "program.UserParts{" +
                "parts=" + Arrays.toString(parts) +
                '}';
    }

    public Point[] getParts()
    {
        return parts;
    }

    // get a specific y in an array of points
    public double getYperso(int index)
    {
        return parts[index].getY();
    }
}
