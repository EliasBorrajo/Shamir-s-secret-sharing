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

    public UserParts()
    {
        partsByUser = new ArrayList<Point>();
    }

    public void setPartsByUser(Point[] partsByUser)
    {
        List<Point> partsByUserList = Arrays.asList(partsByUser);
        ArrayList<Point> partsByUserArrayList = new ArrayList<Point>(partsByUserList);
        this.partsByUser = partsByUserArrayList;

    }

    public ArrayList<Point> getPartsByUser() {
        return partsByUser;
    }

    @Override
    public String toString() {
        return "UserParts{" +
                "partsByUser=" + partsByUser.toString() +
                '}';
    }
}
