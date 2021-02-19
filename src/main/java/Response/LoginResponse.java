package Response;

import Model.Model;


/**
 * A login response sent from the service to the handler
 */
public class LoginResponse implements IResponse{
    /**
     * Represents the model passed from the service
     */
    private Model model;


    /**
     * Instantiates a new Login response.
     *
     * @param model the model passed from the service.
     */
    public LoginResponse(Model model){
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
