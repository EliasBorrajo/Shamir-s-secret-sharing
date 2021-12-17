package ch.hevs.storage;

import ch.hevs.errors.BusinessException;
import ch.hevs.tools.generateParts.GenerateParts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface StorableFiles
{
    /**
     * Read JSON File
     *
     * @throws BusinessException
     * @throws IOException
     */
    ArrayList<GenerateParts> read() throws BusinessException, IOException;

    /**
     * Write JSON File
     *
     * @param destination
     * @param parts
     * @throws BusinessException
     */
    void write(File destination, ArrayList<GenerateParts> parts) throws BusinessException;

}
