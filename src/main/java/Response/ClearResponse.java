package Response;

import Model.Model;

/**
 *  A clear response sent from the service to the handler
 */
public class ClearResponse implements IResponse{
    /**
     * Represents the model passed from the service
     */
    private Model model;


    /**
     * Instantiates a new clear response.
     *
     * @param model the model passed from the service.
     */
    public ClearResponse(Model model){
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
