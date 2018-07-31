package mt.games.qwirkle.resources;

public class ResouceLoadException extends ResourceException {
    public ResouceLoadException(String id, String identifier) {
        super("Failed to load Resource " + id + " at " + identifier + "!", id, identifier);
    }

    public ResouceLoadException(String id, String identifier, Throwable cause) {
        super("Failed to load Resource " + id + " at " + identifier + "!", id, identifier, cause);
    }
}
