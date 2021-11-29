package ch.hevs.errors;

/**
 * Error code for contact and gallery applications
 *
 * @author Bourquin Jonathan
 */

public enum ErrorCode {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    READING_JSON_STORAGE_PART_ERROR(101);




    private final int code;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param code
     */
    ErrorCode(int code) {
        this.code = code;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public int getCode() {
        return code;
    }
}

