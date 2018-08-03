package mt.games.qwirkle.resources;

import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.image.FilteredImageSource;
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
        if (getImage() != null) {
            Image filtered = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(getImage().getImage().getSource(), mColorFilter));
            setImage(new ImageWrapper(getImage().getXSize(), getImage().getYSize(), filtered));
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
            mAlpha = alpha;
            mRed = red;
            mGreen = green;
            mBlue = blue;
        }

        @Override
        public int filterRGB(int x, int y, int rgb) {
            int alpha = (byte) ((rgb & 0xff000000) >> 24);
            int red = (byte) ((rgb & 0xff0000) >> 16);
            int green = (byte) ((rgb & 0xff00) >> 8);
            int blue = (byte) (rgb & 0xff);

            alpha = (Math.round(mAlpha * (alpha / 255f)) * 255);
            red = (Math.round(mRed * (red / 255f)) * 255);
            green = (Math.round(mGreen * (green / 255f)) * 255);
            blue = (Math.round(mBlue * (blue / 255f)) * 255);

            return (alpha << 24) & (red << 16) & (green << 8) & blue;
        }
    }
}
