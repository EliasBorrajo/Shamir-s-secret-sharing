package ch.hevs.maths;

import ch.hevs.tools.generateParts.UserParts;
import java.awt.*;

/**
 * TODO trouver texte
 */
public class SecretRebuilder
{
    //*****************************************************************************
    // A T T R I B U T E S
    //*****************************************************************************
    private Point[] shamirParts;

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * Rebuild the secret
     * @param usersParts
     * @param indexPart column so the IndexShamir
     */
    public void partsRebuilding(UserParts[] usersParts, int indexPart)
    {
        shamirParts = new Point[usersParts.length];

        for (int userIndex = 0; userIndex < usersParts.length; userIndex++)
        {
            shamirParts[userIndex] = usersParts[userIndex].getPartsByUser().get(indexPart);
        }

        //TODO supprimer?
        /*for (int i = 0; i < shamirParts.length; i++) {
            System.out.print(shamirParts[i].toString());
        }
        System.out.println();*/
    }

    //*****************************************************************************
    // G E T T E R S / S E T T E R S
    //*****************************************************************************
    public Point[] getShamirParts() {
        return shamirParts;
    }
}
