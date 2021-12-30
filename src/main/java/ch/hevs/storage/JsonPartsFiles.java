package ch.hevs.storage;

import ch.hevs.errors.BusinessException;
import ch.hevs.parameters.Config;
import ch.hevs.tools.generateParts.UserParts;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO trouver texte
 */
public class JsonPartsFiles implements StorableFiles
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************

    // Object containing user parts
    private UserParts userParts;

    //PATH
    private String storePath;       // Allows to store the content of our SYSTEM ENVIRONMENT VARIABLE
    private String jsonPath;        // Is the variable which will contain the FINAl path on the PC and depending on the OS,
    // to the storage location of our JSON file
    // myObj FILE
    private File myFile;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************

    /**
     * Constructor
     */
    public JsonPartsFiles()
    {
        definePathToStoreData();
    }


    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    // DEFAULT
    public void definePathToStoreData()
    {

        // Retrieves the contents of the ENVIRONMENT VARIABLE
        storePath = Config.getConfig().getStorePath();

        // Will write the path consistently thanks to PATH & PATHS
        // Correctly concatenate my PATH which will be stored in the STRING
        Path path = Paths.get(storePath, "part.json");

        jsonPath = path.toString();

        myFile = new File(jsonPath);
        //System.out.println("REAL PATH OBJECT PART FILE IS : " + myObj.getAbsolutePath());
    }
    // WITH GIVEN NAME for the file
    public void definePathToStoreData(String fileName)
    {
        // Retrieves the contents of the ENVIRONMENT VARIABLE
        storePath = Config.getConfig().getStorePath();

        // Will write the path consistently thanks to PATH & PATHS
        // Correctly concatenate my PATH which will be stored in the STRING
        Path path = Paths.get(storePath, fileName+".json");

        jsonPath = path.toString();

        myFile = new File(jsonPath);
        //System.out.println("REAL PATH OBJECT PART FILE IS : " + myObj.getAbsolutePath());
    }

    @Override
    public UserParts read(File file)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            userParts = mapper.readValue(file, UserParts.class);
        } catch (IOException ioException)
        {
            System.err.println("SERIALISATION of PARTS has failed : ");
            throw new IllegalArgumentException("READ FILES HAS FAILED : "+ioException);
            //ioException.printStackTrace();
            //throw new BusinessException("An error occurred while READING JSON STORAGE PARTS.", ErrorCode.READING_JSON_STORAGE_PART_ERROR);
        }
        return userParts;
    }

    @Override
    public void write(UserParts parts, String fileName)
    {
        // TODO : VERIFIER QUE LE DOSSIER N'EXISTE PAS, ET SI IL EXISTE, En créer un nouveau
        // TODO : Donc avoir un dossier Shamir, qui aura des sous dossiers sh1, sh2, sh3 pour chaque fois que on fait générer
        ObjectMapper mapper = new ObjectMapper();
        definePathToStoreData(fileName);

        try
        {
            mapper.writeValue(myFile, parts);
        } catch (IOException e)
        {
            System.out.println("SERIALISATION of parts.JSON has failed : ");
            e.printStackTrace();
        }
    }

    //*****************************************************************************
    // G E T T E R S / S E T T E R S
    //*****************************************************************************
    public void setMyObj(File myObj) {
        this.myFile = myObj;
    }

    public File getMyObj()
    {
        return myFile;
    }

    public UserParts getUserParts() {
        return userParts;
    }
}
