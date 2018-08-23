package mt.games.qwirkle.resources;


import mt.games.qwirkle.helper.ResourceHelper;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

public class ImageResource extends StreamResource {
    private ImageWrapper mImageWrapper;

    public ImageResource(String identifier, Function<String, InputStream> streamSupplier) {
        super(identifier, streamSupplier);
    }

    public ImageWrapper getImageWrapper() {
        return mImageWrapper;
    }

    protected void setImageWrapper(ImageWrapper image) {
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
                setImageWrapper(new ImageWrapper(img.getWidth(), img.getHeight(), img));
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

    @Nonnull
    private BufferedImage loadImage(@Nonnull String id, @Nonnull InputStream stream) {
        BufferedImage image = ResourceHelper.imageFromStream(stream);
        if (image == null) {
            throw new ResouceLoadException(id, getIdentifier());
        }
        return image;
    }
}
