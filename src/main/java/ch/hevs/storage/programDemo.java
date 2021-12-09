package ch.hevs.storage;

import ch.hevs.errors.BusinessException;
import ch.hevs.parameters.Config;
import ch.hevs.tools.generateParts.UserParts;

public class programDemo {
    public static void main(String[] args) throws BusinessException
    {
        // Creation of the singleton
        Config.getConfig();
        /*
        * ELIAS AJOUT : INITIALISER SINGELTON CONFIG UNE FOIS AU DEPART DE TOUT
        *
        * ENFAITE !!! PAS NECESSAIRE, DEJA FAIT DANS JsonPartsFiles --> definePathToStoreData()
        */


        UserParts userParts = new UserParts(1,0);
        JsonPartsFiles jpf = new JsonPartsFiles();

        System.out.println(userParts.toString());

        // WRITE
        jpf.write(jpf.getMyObj(), userParts); // Rennomer l'attribut OBJET dans JsonPartsFile ? Ici Objet c'est notre File

        // READ
        UserParts userParts1 = jpf.read();

        System.out.println(userParts1.toString());
    }
}
