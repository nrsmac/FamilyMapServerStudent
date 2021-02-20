package Request;

import Model.Model;

/**
 * A register request sent from the handler to the service
 */
public class RegisterRequest implements IRequest{
    /**
     * A convenient place to access the model.
     */
    private Model model;

    /**
     * Creates a new request.
     * @param model
     */
    public RegisterRequest(Model model) {
        this.model = model;
    }

    /**
     * Returns the model passed by the handler.
     * @return a reference to the model
     */
    public Model getRequestModel(){
        return this.model;
    }
}