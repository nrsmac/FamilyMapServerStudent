package Response;

import Model.Model;

/**
 *  A Fill response sent from the service to the handler
 */
public class FillResponse implements IResponse{

    /**
     * Represents the model passed from the service
     */
    private Model model;


    /**
     * Instantiates a new Fill response.
     *
     * @param model the model passed from the service.
     */
    public FillResponse(Model model){
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
