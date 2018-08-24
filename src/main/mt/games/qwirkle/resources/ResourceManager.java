package mt.games.qwirkle.resources;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public enum ResourceManager {
    INSTANCE;
    private Table<Class<? extends IResource>, String, IResource> mResourceMap;

    ResourceManager() {
        mResourceMap = HashBasedTable.create(3, 0);
    }

    public void addResource(@Nonnull Class<? extends IResource> clazz, @Nonnull String name, @Nonnull IResource res) {
        if (mResourceMap.contains(Objects.requireNonNull(clazz), Objects.requireNonNull(name))) {
            throw new IllegalArgumentException("Resource Manager does not permit double entries!");
        }
        mResourceMap.put(clazz, name, Objects.requireNonNull(res));
    }

    public void addResource(@Nonnull String name, @Nonnull IResource res) {
        addResource(Objects.requireNonNull(res).getClass(), name, res);
    }

    @Nullable
    public <T extends IResource> T findResourceFor(Class<T> clazz, String name) {
        return clazz.cast(mResourceMap.get(clazz, name));
    }

    public void load() {
        for (Table.Cell<Class<? extends IResource>, String, IResource> entry : mResourceMap.cellSet()) {
            assert entry.getValue() != null : "Resource Manager does not permit null values! Someone has to have reflected into it!";
            try {
                entry.getValue().load(entry.getColumnKey());
            } catch (Exception e) {
                throw new ResourceException("Resource threw an exception, aborting loading!", entry.getColumnKey(), entry.getValue().getIdentifier(), e);
            }
        }
    }
}
