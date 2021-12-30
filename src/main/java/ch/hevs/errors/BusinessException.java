package ch.hevs.errors;

//import java.io.Serial;

/**
 * Class for businessException
 */
public class BusinessException extends Throwable
{
    //*****************************************************************************
    // A T T R I B U T E S
    //*****************************************************************************
    //@Serial
    private static final long serialVersionUID = -446022369330950597L;

    private final ErrorCode errorCode;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param msg
     * @param errorCode
     */
    public BusinessException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
