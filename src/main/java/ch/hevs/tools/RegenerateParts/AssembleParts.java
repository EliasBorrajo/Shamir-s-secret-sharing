package ch.hevs.tools.RegenerateParts;

import ch.hevs.Maths.LagrangeInterpolation;
import ch.hevs.Maths.SecretRebuilder;
import ch.hevs.tools.generateParts.UserParts;


public class AssembleParts {
    private byte[] secret;
    private LagrangeInterpolation li;
    private SecretRebuilder[] sr;
    //private int nbBytes;

    public AssembleParts() {
        //this.nbBytes = nbBytes;

        sr = new SecretRebuilder[32];
        secret = new byte[32];
        li = new LagrangeInterpolation();
    }

    // METHODS
    public void secret(UserParts[] usersParts)
    {
        for (int i = 0; i < 32; i++) {
            sr[i] = new SecretRebuilder();

            sr[i].partsRebuilding(usersParts, i); // reconstruit les parts de secrets de chaque utilisateurs pour chaque fi()
            // --> rempli la variable shamir parts de chaque objet SecretRebuilder

            // applique lagrange sur chaque shamirparts de chaque objet Secretrebuilder pour générer le tableau des secrets de chaque fi()
            secret[i] = (byte) li.lagrange(sr[i].getShamirParts(), usersParts.length+1); // TODO: 20.12.2021 problème avec le seuil ? comment entrer ce paramètre et le controler ?
        }
    }

    public void affiche(byte[] tab)
    {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }

    // GETTER
    public byte[] getSecret() {
        return secret;
    }

}
