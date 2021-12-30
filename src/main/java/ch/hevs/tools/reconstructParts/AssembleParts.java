package ch.hevs.tools.reconstructParts;

import ch.hevs.maths.LagrangeInterpolation;
import ch.hevs.storage.UserParts;

/**
 * TODO completer
 * Class that assembles users parts
 */
public class AssembleParts
{
    //*******************************************************************************
    //  A T T R I B U T E S
    //*******************************************************************************
    private byte[] secret;
    private LagrangeInterpolation li;
    private SecretRebuilder[] sr;
    private int nbBytes;


    //*******************************************************************************
    //  C O N S T R U C T O R
    //*******************************************************************************
    /**
     * Assemble parts
     * @param nbBytes
     */
    public AssembleParts(int nbBytes) {
        this.nbBytes = nbBytes;
        sr = new SecretRebuilder[this.nbBytes];
        secret = new byte[this.nbBytes];
        li = new LagrangeInterpolation();
    }

    //*******************************************************************************
    //  M E T H O D S
    //*******************************************************************************
    /**
     * Reconstruction of the secret and use of Lagrange
     * @param usersParts
     */
    public void secret(UserParts[] usersParts)
    {
        for (int i = 0; i < nbBytes; i++)
        {
            sr[i] = new SecretRebuilder();

            sr[i].partsRebuilding(usersParts, i); // reconstruit les parts de secrets de chaque utilisateurs pour chaque fi()
            // --> rempli la variable shamir parts de chaque objet SecretRebuilder

            // applique lagrange sur chaque shamirparts de chaque objet Secretrebuilder pour générer le tableau des secrets de chaque fi()
            secret[i] = (byte) li.lagrange(sr[i].getShamirParts(), usersParts.length + 1);
        }
    }

    public void affiche(byte[] tab)
    {
        for (int i = 0; i < tab.length; i++)
        {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }

    // GETTER
    public byte[] getSecret()
    {
        return secret;
    }

}
