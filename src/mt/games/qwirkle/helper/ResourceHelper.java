package mt.games.qwirkle.helper;

import com.sun.istack.internal.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class ResourceHelper {

    private ResourceHelper() {
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
