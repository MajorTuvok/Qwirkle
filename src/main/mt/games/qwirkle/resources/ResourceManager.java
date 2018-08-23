package mt.games.qwirkle.resources;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum ResourceManager {
    INSTANCE;
    private Map<String, IResource> mResourceMap;

    ResourceManager() {
        mResourceMap = new HashMap<>();
    }

    public void addResource(@Nonnull String name, @Nonnull IResource res) {
        mResourceMap.put(Objects.requireNonNull(name), Objects.requireNonNull(res));
    }

    @Nullable
    public IResource findResourceFor(String name) {
        return mResourceMap.get(name);
    }

    public void load() {
        for (Map.Entry<String, IResource> entry : mResourceMap.entrySet()) {
            try {
                entry.getValue().load(entry.getKey());
            } catch (Exception e) {
                throw new ResourceException("Resource threw an exception, aborting loading!", entry.getKey(), entry.getValue().getIdentifier(), e);
            }
        }
    }
}
