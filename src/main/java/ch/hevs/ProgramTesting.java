package ch.hevs;

import ch.hevs.Maths.SecretRebuilder;
import ch.hevs.tools.generateParts.UserParts;

import java.awt.*;

public class ProgramTesting {
    public static void main(String[] args)
    {

        RegenerateWithGivenParts(true);

    }

    private static void RegenerateWithGivenParts(/*File[] userFiles, File fileToCrypt,*/ boolean toEncrypt)
    {
        UserParts[] usersParts = new UserParts[4];
        SecretRebuilder[] sr = new SecretRebuilder[32];

        for (int i= 0; i < usersParts.length; i++)
        {
            usersParts[i] = new UserParts(i,0);
        }

        afficheParts(usersParts);

        //afficheByte(sr.finalSecret(usersParts));
        System.out.println("AFFICHAGE DES COLONNES");
        for (int shamirIndex = 0; shamirIndex < sr.length; shamirIndex++)
        {
            sr[shamirIndex] = new SecretRebuilder(3);

            Point[] yolo = sr[shamirIndex].partsRebuilding(usersParts, shamirIndex);
            System.out.println();
        }
    }


    private static void afficheParts(UserParts[] usersParts){
        for (int i = 0; i < usersParts.length; i++)
        {
            for (int j = 0; j < 32; j++)
            {
                System.out.print(usersParts[i].getParts()[j].toString());
            }
            System.out.println();
        }

    }

    private static void afficheByte(int[] bytes)
    {
        for (int i = 0; i < bytes.length; i++)
        {
            System.out.print(bytes[i]);
        }
        System.out.println();
    }
}
