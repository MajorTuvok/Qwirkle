package mt.games.qwirkle.resources;

import com.sun.istack.internal.NotNull;
import mt.games.qwirkle.helper.ResourceHelper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

public class ImageResource extends StreamResource {
    private ImageWrapper mImageWrapper;

    public ImageResource(String identifier, Function<String, InputStream> streamSupplier) {
        super(identifier, streamSupplier);
    }

    public ImageWrapper getImage() {
        return mImageWrapper;
    }

    protected void setImage(ImageWrapper image) {
        mImageWrapper = image;
    }

    @Override
    public void load(String id) {
            InputStream stream = null;
            try {
                stream = getStream();
                if (stream == null) {
                    throw new ResourceNotFoundException(id, getIdentifier());
                }
                BufferedImage img = loadImage(id, stream);
                setImage(new ImageWrapper(img.getWidth(), img.getHeight(), img));
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

    @NotNull
    private BufferedImage loadImage(@NotNull String id, @NotNull InputStream stream) {
        BufferedImage image = ResourceHelper.imageFromStream(stream);
        if (image == null) {
            throw new ResouceLoadException(id, getIdentifier());
        }
        return image;
    }
}
