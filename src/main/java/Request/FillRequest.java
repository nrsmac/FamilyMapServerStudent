package Request;

/**
 *  A fill request sent from the handler to the service
 */
public class FillRequest implements IRequest{
    private String username;
    private int generations = 4;

    public FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    public FillRequest(String username) {
        this.username = username;
    }

    public int getGenerations() {
        return generations;
    }

    public String getUsername() {
        return username;
    }
}
