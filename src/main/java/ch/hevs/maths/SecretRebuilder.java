package ch.hevs.maths;

import ch.hevs.tools.generateParts.UserParts;
import java.awt.*;

public class SecretRebuilder //TODO : Doit aller dans package STORAGE
{
    private Point[] shamirParts;

    // indexParts Sera la colonne, donc le IndexShamir
    public void partsRebuilding(UserParts[] usersParts, int indexPart)
    {
        shamirParts = new Point[usersParts.length];

        for (int userIndex = 0; userIndex < usersParts.length; userIndex++)
        {
            shamirParts[userIndex] = usersParts[userIndex].getPartsByUser().get(indexPart);
        }

        /*for (int i = 0; i < shamirParts.length; i++) {
            System.out.print(shamirParts[i].toString());
        }
        System.out.println();*/
    }

    // GETTER
    public Point[] getShamirParts() {
        return shamirParts;
    }
}
