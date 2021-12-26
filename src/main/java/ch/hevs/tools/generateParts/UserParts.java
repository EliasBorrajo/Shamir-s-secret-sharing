package ch.hevs.tools.generateParts;

import java.awt.*;
import java.util.Arrays;

/**
 * Class for user parts. Each object will be an instanciate a piece of secret (Point array with the same X coordinate for each point and a specific Y coordinate)
 * that will be exported in a json file
 */
public class UserParts
{
    private Point[] partsByUser; // pos 1 --> part shamir 1 XY
                                 // pos 2 --> part shamir 2 XY

    public UserParts(int nbBytes)
    {
        partsByUser = new Point[nbBytes];
    }

    public Point[] getParts()
    {
        return partsByUser;
    }

    public void setPartsByUser(Point[] partsByUser)
    {
        this.partsByUser = partsByUser;
    }

    // get a specific y in an array of points
    public double getYperso(int index)
    {
        return partsByUser[index].getY();
    }

    @Override
    public String toString()
    {
        return "program.UserParts{" +
                "parts=" + Arrays.toString(partsByUser) +
                '}';
    }
}
