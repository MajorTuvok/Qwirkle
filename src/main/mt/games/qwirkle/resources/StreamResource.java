package mt.games.qwirkle.resources;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Function;

public abstract class StreamResource implements IResource {
    private final String mIdentifier;
    private final Function<String, InputStream> mStreamSupplier;

    public StreamResource(@Nonnull String identifier, Function<String, InputStream> streamSupplier) {
        mIdentifier = Objects.requireNonNull(identifier);
        if (mIdentifier.isEmpty()) {
            throw new IllegalArgumentException("Cannot have an Empty StreamResource URL!");
        }
        mStreamSupplier = streamSupplier;
    }

    @Override
    public String getIdentifier() {
        return mIdentifier;
    }

    public Function<String, InputStream> getStreamSupplier() {
        return mStreamSupplier;
    }

    @Nullable
    public InputStream getStream() {
        return getStreamSupplier().apply(getIdentifier());
    }
}
