package ch.hevs.tools.generateParts;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for user parts. Each object will be an instanciate a piece of secret (Point array with the same X coordinate for each point and a specific Y coordinate)
 * that will be exported in a json file
 */
public class UserParts
{
    private ArrayList<Point> partsByUser; // pos 1 --> part shamir 1 XY
                                          // pos 2 --> part shamir 2 XY

    public UserParts(/*int nbBytes*/)
    {
        partsByUser = new ArrayList<Point>();
        //partsByUser = new Point[nbBytes];
    }



    public void setPartsByUser(Point[] partsByUser)
    {
        List<Point> temp = Arrays.asList(partsByUser);
        this.partsByUser = new ArrayList<Point>(temp) ;
        this.toString();
    }

    @Override
    public String toString()
    {
        return "UserParts{" +
                "partsByUser=" + partsByUser +
                '}';
    }
}
