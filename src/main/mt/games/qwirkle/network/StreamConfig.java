package mt.games.qwirkle.network;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public final class StreamConfig {
    @Nullable
    private PipedInputStream mInputStream;
    @Nullable
    private PipedOutputStream mOutputStream;

    public StreamConfig(@Nonnull PipedInputStream inputStream, @Nonnull PipedOutputStream outputStream) {
        mInputStream = inputStream;
        mOutputStream = outputStream;
    }

    public static StreamConfig newStreamInstance() {
        return new StreamConfig(new PipedInputStream(), new PipedOutputStream());
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

    @Nonnull
    public PipedInputStream getInputStream() {
        if (mInputStream == null) {
            throw new IllegalStateException("Cannot access the already connected Stream! Pass this Object to the Server first and Client second!");
        }
        return mInputStream;
    }

    @Nonnull
    public PipedOutputStream getOutputStream() {
        if (mOutputStream == null) {
            throw new IllegalStateException("Cannot access the already connected Stream! Pass this Object to the Server first and Client second!");
        }
        return mOutputStream;
    }
}
