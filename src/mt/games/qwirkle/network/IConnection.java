package mt.games.qwirkle.network;

import com.sun.istack.internal.NotNull;

import java.io.InputStream;
import java.io.OutputStream;

public interface IConnection extends AutoCloseable {
    @NotNull
    public InputStream getInputStream();

    @NotNull
    public OutputStream getOutputStream();
}
