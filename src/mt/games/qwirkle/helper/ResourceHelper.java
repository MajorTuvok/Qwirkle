package mt.games.qwirkle.helper;

import com.sun.istack.internal.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.function.Function;

public final class ResourceHelper {
    public static final Function<String, InputStream> EXTERNAL_SUPPLIER = ResourceHelper::getFileStream;
    public static final Function<String, InputStream> INTERNAL_SUPPLIER = ResourceHelper::getInternalStream;

    private ResourceHelper() {
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

    private static ClassLoader getLoader() {
        return ResourceHelper.class.getClassLoader();
    }
}
