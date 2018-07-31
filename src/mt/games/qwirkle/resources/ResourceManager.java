package mt.games.qwirkle.resources;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum ResourceManager {
    INSTANCE;
    private Map<String, IResource> mResourceMap;

    ResourceManager() {
        mResourceMap = new HashMap<>();
    }

    public void addResource(@NotNull String name, @NotNull IResource res) {
        mResourceMap.put(Objects.requireNonNull(name), Objects.requireNonNull(res));
    }

    @Nullable
    public IResource findResourceFor(String name) {
        return mResourceMap.get(name);
    }

    public void load() {
        for (Map.Entry<String, IResource> entry : mResourceMap.entrySet()) {
            entry.getValue().load(entry.getKey());
        }
    }
}
