package Request;

import Model.Model;

/**
 * A register request sent from the handler to the service
 */
public class ClearRequest implements IRequest{
    /**
     * A convenient place to access the model.
     */
    private Model model;

    /**
     * Creates a request.
     * @param model
     */
    public ClearRequest(Model model) {
        this.model = model;
    }

    /**
     * Returns the model passed by the handler.
     * @return
     */
    public Model getRequestModel(){
        return this.model;
    }
}
