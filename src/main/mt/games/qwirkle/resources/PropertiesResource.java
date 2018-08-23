package mt.games.qwirkle.resources;


import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.function.Function;

public class PropertiesResource extends StreamResource {
    private Properties mProperties;

    public PropertiesResource(String identifier, Function<String, InputStream> streamSupplier) {
        super(identifier, streamSupplier);
    }

    public Properties getProperties() {
        return mProperties;
    }

    public void setProperties(@Nullable Properties properties) {
        mProperties = properties;
    }

    @Override
    public void load(String id) {
        if (mProperties == null) {
            mProperties = new Properties();
            InputStream stream = null;
            try {
                stream = getStream();
                if (stream == null) {
                    throw new ResourceNotFoundException(id, getIdentifier());
                }
                try {
                    mProperties.load(stream);
                } catch (IOException e) {
                    throw new ResouceLoadException(id, getIdentifier(), e);
                }
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
