package ch.hevs.Maths;

import ch.hevs.storage.JsonPartsFiles;
import ch.hevs.tools.generateParts.UserParts;

import java.awt.*;

public class SecretRebuilder {
    private LagrangeInterpolation li;
    private UserParts[] usersParts;
    private int nbPartsInput;

    public SecretRebuilder(int nbPartsInput)
    {
        this.nbPartsInput = nbPartsInput;
        li = new LagrangeInterpolation();
        usersParts = new UserParts[nbPartsInput];
    }

    public Point[] partsRebuilding()
    {
        Point[] shamirParts = new Point[usersParts.length];

        for (int i = 0; i < usersParts.length; i++)
        {
            shamirParts[i] = usersParts[i].getParts()[i];
        }

        return shamirParts;
    }

    public byte secretBuilding(Point[] points)
    {
        return (byte) li.lagrange(points, nbPartsInput);
    }

}
