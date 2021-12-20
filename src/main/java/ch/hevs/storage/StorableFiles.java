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
    UserParts read(File file) throws BusinessException, IOException;

    /**
     * Write JSON File
     *
     * @param parts
     * @throws BusinessException
     */
    void write(UserParts parts) throws BusinessException;

}
