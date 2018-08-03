package mt.games.qwirkle.resources;

public class ResourceException extends RuntimeException {
    private final String mId;
    private final String mIdentifier;

    public ResourceException(String message, String id, String identifier, Throwable cause) {
        super(message, cause);
        this.mId = id;
        this.mIdentifier = identifier;
    }

    public ResourceException(String message, String id, String identifier) {
        super(message);
        this.mId = id;
        this.mIdentifier = identifier;
    }

    public String getId() {
        return mId;
    }

    public String getIdentifier() {
        return mIdentifier;
    }
}
