package Response;

import Model.Model;

/**
 * A register response sent from the service to the handler
 */
public class RegisterResponse implements IResponse{
    /**
     * Represents the model passed from the service
     */
    private Model model;


    /**
     * Instantiates a new Register response.
     *
     * @param model the model passed from the service.
     */
    public RegisterResponse(Model model){
        this.model = model;
    }

    /**
     * Gets response model.
     *
     * @return the response model
     */
    public Model getResponseModel() {
        return this.model;
    }
}
