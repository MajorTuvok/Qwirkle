package mt.games.qwirkle.resources;


import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Function;

public class FilteredImageResource extends ImageResource {
    private ColorFilter mColorFilter;

    public FilteredImageResource(Color c, String identifier, Function<String, InputStream> streamSupplier) {
        super(identifier, streamSupplier);
        mColorFilter = new ColorFilter(Objects.requireNonNull(c));
    }

    @Override
    public void load(String id) {
        super.load(id);
        if (getImageWrapper() != null) {
            ImageWrapper curImageWrapper = getImageWrapper();
            ImageProducer prod = curImageWrapper.getImage().getSource();
            Image filtered = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(prod, mColorFilter));
            setImageWrapper(new ImageWrapper(curImageWrapper.getXSize(), curImageWrapper.getYSize(), filtered));
        }
    }

    public static class ColorFilter extends RGBImageFilter {
        private final int mAlpha;
        private final int mBlue;
        private final int mGreen;
        private final int mRed;

        public ColorFilter(@Nonnull Color color) {
            this(color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue());
        }

        public ColorFilter(int alpha, int red, int green, int blue) {
            super();
            mAlpha = alpha;
            mRed = red;
            mGreen = green;
            mBlue = blue;
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int x, int y, int rgba) {
            int red = (rgba) & 0xFF;
            int green = (rgba >> 8) & 0xFF;
            int blue = (rgba >> 16) & 0xFF;
            int alpha = (rgba >> 24) & 0xFF;

            red = Math.round(mRed * red / 255f);
            green = Math.round(mGreen * green / 255f);
            blue = Math.round(mBlue * blue / 255f);
            alpha = Math.round(mAlpha * alpha / 255f);

            return red | (green << 8) | (blue << 16) | (alpha << 24);
        }
    }
}
