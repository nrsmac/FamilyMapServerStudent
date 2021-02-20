package Request;

import Model.Model;

/**
 *  A fill request sent from the handler to the service
 */
public class FillRequest implements IRequest{
    /**
     * A convenient place to access the model.
     */
    private Model model;

    /**
     * Creates a new request.
     * @param model
     */
    public FillRequest(Model model) {
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
