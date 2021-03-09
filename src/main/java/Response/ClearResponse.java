package Response;

/**
 *  A clear response sent from the service to the handler
 */
public class ClearResponse implements IResponse{
    private String message;
    private boolean success;
    /**
     * Instantiates a new clear response.
     *
     * @param message
     * @param success
     */
    public ClearResponse(String message, boolean success){
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
