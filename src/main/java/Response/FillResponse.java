package Response;

/**
 *  A Fill response sent from the service to the handler
 */
public class FillResponse implements IResponse{
    private String message;
    private boolean success;

    public FillResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
