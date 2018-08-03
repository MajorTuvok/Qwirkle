package mt.games.qwirkle.network;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public final class StreamConfig {
    private PipedInputStream mInputStream;
    private PipedOutputStream mOutputStream;

    public StreamConfig(PipedInputStream inputStream, PipedOutputStream outputStream) {
        mInputStream = inputStream;
        mOutputStream = outputStream;
    }

    public void connectTo(PipedInputStream otherIn, PipedOutputStream otherOut) throws IOException {
        if (mInputStream == null || mOutputStream == null) {
            throw new IllegalStateException("Cannot connect twice!");
        }
        mInputStream.connect(otherOut);
        otherIn.connect(mOutputStream);
        mInputStream = null;
        mOutputStream = null;
    }
}
