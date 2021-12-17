package ch.hevs.storage;

import ch.hevs.errors.BusinessException;
import ch.hevs.tools.generateParts.GenerateParts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
public class JsonPartsFiles implements StorableFiles
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // ARRAY LIST - It will be our Address book
    private ArrayList<Contact> contactArray = new ArrayList<>();

    // List which allows to read the JSON and will then be converted into an ArrayList of the address book
    private List<Contact> contactList;

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
/*    public JSONStorageContact() {
        definePathToStoreData();
        try {
            read();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        sortDescending(contactArray);
    }
    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************

    @Override
    public ArrayList<GenerateParts> read() throws BusinessException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            //mapper.writeValue(destination, GenerateParts);
        }
        catch (IOException ioException)
        {
            System.err.println("SERIALISATION of PARTS has failed : ");
            ioException.printStackTrace();
            //throw new BusinessException("Serialization of parts in files JSON failed");
        }

        return null;
    }

    @Override
    public void write(File destination, ArrayList<GenerateParts> parts) throws BusinessException
    {

    }
}
*/