package ch.hevs.storage;

import ch.hevs.errors.BusinessException;
import ch.hevs.errors.ErrorCode;
import ch.hevs.parameters.Config;
import ch.hevs.tools.generateParts.UserParts;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class JsonPartsFiles implements StorableFiles
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************

    // Object containing user parts
    private UserParts userParts = new UserParts();

    //PATH
    private String storePath;       // Allows to store the content of our SYSTEM ENVIRONMENT VARIABLE
    private String jsonPath;        // Is the variable which will contain the FINAl path on the PC and depending on the OS,
    // to the storage location of our JSON file
    // myObj FILE
    private File myObj;

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

    public void definePathToStoreData()
    {
        // Retrieves the contents of the ENVIRONMENT VARIABLE
        storePath = Config.getConfig().getStorePath();

        // Will write the path consistently thanks to PATH & PATHS
        // Correctly concatenate my PATH which will be stored in the STRING
        Path path = Paths.get(storePath, "part.json");

        jsonPath = path.toString();

        myObj = new File(jsonPath);
        System.out.println("REAL PATH OBJECT PART FILE IS : " + myObj.getAbsolutePath());
    }

    @Override
    public UserParts read() throws BusinessException
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            userParts = mapper.readValue(myObj, UserParts.class);
        } catch (IOException ioException)
        {
            System.err.println("SERIALISATION of PARTS has failed : ");
            ioException.printStackTrace();
            throw new BusinessException("An error occurred while READING JSON STORAGE PARTS.", ErrorCode.READING_JSON_STORAGE_PART_ERROR);
        }
        return userParts;
    }

    @Override
    public void write(File destination, UserParts parts) throws BusinessException
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(destination, parts);
        } catch (IOException e)
        {
            System.out.println("SERIALISATION of parts.JSON has failed : ");
            e.printStackTrace();
        }
    }

    public File getMyObj()
    {
        return myObj;
    }
}
