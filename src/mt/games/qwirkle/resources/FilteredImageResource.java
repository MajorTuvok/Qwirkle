package mt.games.qwirkle.resources;

import com.sun.istack.internal.NotNull;

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
        private final float mAlpha;
        private final float mBlue;
        private final float mGreen;
        private final float mRed;

        public ColorFilter(@NotNull Color color) {
            this(color.getAlpha() / 255f, color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
        }

        public ColorFilter(float alpha, float red, float green, float blue) {
            super();
            mAlpha = alpha;
            mRed = red;
            mGreen = green;
            mBlue = blue;
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int x, int y, int argb) {
            int red = (argb) & 0xFF;
            int green = (argb >> 8) & 0xFF;
            int blue = (argb >> 16) & 0xFF;
            int alpha = (argb >> 24) & 0xFF;

            alpha = (Math.round(mAlpha * (alpha / 255f)) * 255);
            red = (Math.round(mRed * (red / 255f)) * 255);
            green = (Math.round(mGreen * (green / 255f)) * 255);
            blue = (Math.round(mBlue * (blue / 255f)) * 255);

            return (alpha << 24) | (red << 16) | (green << 8) | blue;
        }
    }
}
