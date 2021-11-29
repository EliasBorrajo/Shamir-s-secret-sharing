package ch.hevs.storage;

import ch.hevs.errors.BusinessException;
import ch.hevs.tools.generateParts.UserParts;

import java.io.File;
import java.io.IOException;

public interface StorableFiles
{
    /**
     * Read JSON File
     *
     * @throws BusinessException
     * @throws IOException
     */
    UserParts read() throws BusinessException, IOException;

    /**
     * Write JSON File
     *
     * @param destination
     * @param parts
     * @throws BusinessException
     */
    void write(File destination, UserParts parts) throws BusinessException;

}
