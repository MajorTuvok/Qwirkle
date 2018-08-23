package mt.games.qwirkle.network.communication;

import mt.games.qwirkle.network.ICloseCallback;

import java.io.Closeable;
import java.io.InputStream;

public class StreamParser implements ICloseCallback {
    private InputStream mStream;

    public StreamParser(InputStream stream) {
        mStream = stream;
    }

    @Override
    public void onResourceClosed(Closeable resource) {

    }
}
