package ch.hevs;

import ch.hevs.Maths.SecretRebuilder;
import ch.hevs.tools.generateParts.UserParts;

import java.awt.*;

public class ProgramTesting {
    public static void main(String[] args) {
        UserParts[] usersParts = new UserParts[4];
        SecretRebuilder sr = new SecretRebuilder(3);
        Point[][] AllParts = new Point[3][32];

        for (int i= 0; i < usersParts.length; i++)
        {
            usersParts[i] = new UserParts();
        }

        affiche(usersParts);






    }



    private static void affiche(UserParts[] usersParts){
        for (int i = 0; i < usersParts.length; i++)
        {
            for (int j = 0; j < 32; j++)
            {
                System.out.print(usersParts[i].getParts()[j].toString());
            }
            System.out.println();
        }

    }
}
