package mt.games.qwirkle.resources;

import java.awt.*;

public final class ImageWrapper {
    private final Image mImage;
    private final int mXSize;
    private final int mYSize;

    public ImageWrapper(int XSize, int YSize, Image image) {
        mXSize = XSize;
        mYSize = YSize;
        mImage = image;
    }

    public int getXSize() {
        return mXSize;
    }

    public int getYSize() {
        return mYSize;
    }

    public Image getImage() {
        return mImage;
    }

    @Override
    public int hashCode() {
        int result = getXSize();
        result = 31 * result + getYSize();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageWrapper)) return false;

        ImageWrapper that = (ImageWrapper) o;

        if (getXSize() != that.getXSize()) return false;
        return getYSize() == that.getYSize();
    }

    @Override
    public String toString() {
        return "ImageWrapper{" +
                "mXSize=" + mXSize +
                ", mYSize=" + mYSize +
                '}';
    }
}
