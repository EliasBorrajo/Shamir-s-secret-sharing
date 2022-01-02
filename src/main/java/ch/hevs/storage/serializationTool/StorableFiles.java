package ch.hevs.storage.serializationTool;

import ch.hevs.errors.BusinessException;
import ch.hevs.storage.UserParts;

import java.io.File;
import java.io.IOException;

/**
 * Interface for our Serialization.
 * @author : Elias Borrajo
 */
public interface StorableFiles
{
    /**
     * Read JSON File
     *
     * @throws BusinessException
     * @throws IOException
     */
    UserParts read(File file);

    /**
     * Write JSON File
     *
     * @param parts
     * @throws BusinessException
     */
    void write(UserParts parts, String fileName) ;

}
