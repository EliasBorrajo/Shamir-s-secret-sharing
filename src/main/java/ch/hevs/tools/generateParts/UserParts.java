package ch.hevs.tools.generateParts;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for user parts.
 * Each object will be an instanciate a piece of secret (Point array with the same X coordinate for each point and a specific Y coordinate)
 * that will be exported in a json file.
 */

public class UserParts
{
    //*****************************************************************************
    // A T T R I B U T E S
    //*****************************************************************************
    private  int threshold;     //MetaData - Donner le threshold pour que à la reconstruction du file, on ne puisse pas encrypter / decrypter sans avoir la valeur du threshold
    private ArrayList<Point> partsByUser; // pos 1 --> part shamir 1 XY
                                 // pos 2 --> part shamir 2 XY

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    public UserParts()
    {
        partsByUser = new ArrayList<Point>();
    }
    public UserParts(Point[] partsInput, int thresholdInput)
    {
        setPartsByUser(partsInput);
        setThreshold(thresholdInput);
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    public void setPartsByUser(Point[] partsByUser)
    {
        List<Point> partsByUserList = Arrays.asList(partsByUser);
        ArrayList<Point> partsByUserArrayList = new ArrayList<Point>(partsByUserList);
        this.partsByUser = partsByUserArrayList;

    }

    @Override
    public String toString() {
        return "UserParts{" +
                "partsByUser=" + partsByUser.toString() +
                '}';
    }

    //*****************************************************************************
    // G E T T E R S / S E T T E R S
    //*****************************************************************************
    public ArrayList<Point> getPartsByUser() {
        return partsByUser;
    }
    public  int getThreshold()
    {
        return threshold;
    }
    public void setThreshold(int threshold)
    {
        this.threshold = threshold;
    }
}
