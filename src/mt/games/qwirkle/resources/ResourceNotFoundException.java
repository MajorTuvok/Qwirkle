package mt.games.qwirkle.resources;

public class ResourceNotFoundException extends ResourceException {
    public ResourceNotFoundException(String id, String identifier) {
        super("Could not find Resource " + id + " at " + identifier + "! Please check your Resource Declarations!", id, identifier);
    }
}
