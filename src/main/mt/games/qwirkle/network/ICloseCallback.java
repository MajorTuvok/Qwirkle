package mt.games.qwirkle.network;

import java.io.Closeable;

public interface ICloseCallback {
    public void onResourceClosed(Closeable resource);
}
