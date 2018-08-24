package mt.games.qwirkle.helper;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.util.function.Function;

public final class ResourceHelper {
    public static final Function<String, InputStream> EXTERNAL_SUPPLIER = ResourceHelper::getFileStream;
    @Nullable
    private static String RUN_DIR;
    public static final Function<String, InputStream> INTERNAL_SUPPLIER = ResourceHelper::getInternalStream;
    public static final Function<String, InputStream> EXTERNAL_REL_SUPPLIER = ResourceHelper::getFileStreamRel;

    static {
        try {
            RUN_DIR = new File(ResourceHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            RUN_DIR = null;
        }
    }

    private ResourceHelper() {
        throw new AssertionError();
    }

    @Nonnull
    public static String getRunDir() {
        if (RUN_DIR == null) {
            throw new IllegalStateException("Tried to retrieve run Directory form an unknown Location!");
        }
        return RUN_DIR;
    }

    public static BufferedImage readInternalImage(String res) {
        InputStream stream = getInternalStream(res);
        return imageFromStream(stream);
    }

    public static BufferedImage readExternalImage(String res) {
        InputStream stream = getInternalStream(res);
        return imageFromStream(stream);
    }

    @Nullable
    public static BufferedImage imageFromStream(InputStream stream) {
        if (stream == null) {
            return null;
        }
        try {
            BufferedImage res = ImageIO.read(stream);
            if (res != null) {
                return res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static InputStream getInternalStream(String res) {
        return getLoader().getResourceAsStream(res);
    }

    @Nullable
    public static InputStream getFileStream(String res) {
        try {
            return new FileInputStream(new File(res));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static InputStream getFileStreamRel(String res) {
        try {
            return new FileInputStream(new File(getRunDir() + res));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ClassLoader getLoader() {
        return ResourceHelper.class.getClassLoader();
    }
}
