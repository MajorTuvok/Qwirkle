package mt.games.qwirkle.network;


import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IConnection extends AutoCloseable {
    @Nonnull
    public InputStream getInputStream();

    @Nonnull
    public OutputStream getOutputStream();

    @Override
    void close() throws IOException;
}
