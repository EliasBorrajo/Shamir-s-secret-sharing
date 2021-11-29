package ch.hevs.storage;

import ch.hevs.errors.BusinessException;
import ch.hevs.tools.generateParts.UserParts;

public class programDemo {
    public static void main(String[] args) throws BusinessException {

        UserParts userParts = new UserParts();
        JsonPartsFiles jpf = new JsonPartsFiles();

        System.out.println(userParts.toString());

        jpf.write(jpf.getMyObj(), userParts);

        UserParts userParts1 = jpf.read();

        System.out.println(userParts1.toString());
    }
}
