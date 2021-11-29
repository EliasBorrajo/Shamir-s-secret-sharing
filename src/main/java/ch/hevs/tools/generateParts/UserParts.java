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

    public UserParts()
    {
        parts = new Point[32];

        for (int i = 0; i < parts.length; i++)
        {
            parts[i] = new Point(2, (int) (Math.random() * 257)); // testing with random values for user 2 (X = 2)
        }

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
}
