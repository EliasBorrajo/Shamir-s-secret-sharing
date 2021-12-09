package ch.hevs.Maths;

import ch.hevs.tools.generateParts.UserParts;
import java.awt.*;

public class SecretRebuilder {
    private LagrangeInterpolation li;
    private int nbPartsInput;
    private Point[] shamirParts;

    public SecretRebuilder(int nbPartsInput)
    {
        this.nbPartsInput = nbPartsInput;
        shamirParts = new Point[nbPartsInput];
        li = new LagrangeInterpolation();
    }

    public int[] finalSecret(UserParts[] usersParts)
    {
        int[] secret = new int[32];

        for (int i = 0; i < secret.length; i++)
        {
            secret[i] = secretBuilding(partsRebuilding(usersParts, i), i);
        }

        return secret;
    }

    // indexParts Sera la colonne, donc le IndexShamir
    public Point[] partsRebuilding(UserParts[] usersParts, int indexPart)
    {
        //Point[] shamirParts = new Point[usersParts.lenght];

        for (int userIndex = 0; userIndex < usersParts.length; userIndex++)
        {
            shamirParts[userIndex] = usersParts[userIndex].getParts()[indexPart];
        }

        for (int i = 0; i < shamirParts.length; i++) {
            System.out.print(shamirParts[i].toString());
        }
        System.out.println();



        return shamirParts; // @TODO : ne plus retourner, mais stocker les resultats en attribut de cette classe
    }

    public int secretBuilding(Point[] points, int index)
    {
        return  li.lagrange(points, nbPartsInput);
    }

}
