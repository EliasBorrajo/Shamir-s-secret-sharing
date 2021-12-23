package ch.hevs.errors;

/**
<<<<<<< HEAD
 * Error code for contact and gallery applications
=======
 * Error code
>>>>>>> JonathanTestElias
 *
 * @author Bourquin Jonathan
 */

public enum ErrorCode {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    READING_JSON_STORAGE_GENERATEPART_ERROR(101),
    CIPHERTEXT_ERROR(100),
    BAD_PADDING_ERROR(101),
    ILLEGAL_BLOCK_SIZE_ERROR(102),
    CRYPTO_PROVIDER_ERROR(103),
    KEYSTORE_ERROR(104),
    IO_ERROR(200),
    INVALID_PARAMETER_ERROR(300);

    private final int code;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************

    /**
     * Constructor
     *
     * @param code
     */
    ErrorCode(int code)
    {
        this.code = code;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public int getCode()
    {
        return code;
    }
}

