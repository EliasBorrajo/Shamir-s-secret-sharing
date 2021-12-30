package ch.hevs.tools.reconstructParts;

import ch.hevs.storage.UserParts;
import java.awt.*;
/**
 * TODO Trouver texte
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


    }

    //*****************************************************************************
    // G E T T E R S / S E T T E R S
    //*****************************************************************************
    public Point[] getShamirParts() {
        return shamirParts;
    }
}
