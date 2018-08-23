package mt.games.qwirkle.network.server;

import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.StreamConfig;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;

public class ServerStreamConnector implements IConnector<StreamConfig, Object> {

    @Override
    public Object prepareConnect(StreamConfig streamConfig) {
        return null;
    }

    @Nullable
    @Override
    public IConnection tryConnect(@Nonnull StreamConfig streamConfig, Object preparedData) {
        PipedInputStream inputStream = streamConfig.getInputStream();
        PipedOutputStream outputStream = streamConfig.getOutputStream();
        return new IConnection() {
            @Override
            @Nonnull
            public InputStream getInputStream() {
                return inputStream;
            }

            @Override
            @Nonnull
            public OutputStream getOutputStream() {
                return outputStream;
            }

            @Override
            public void close() throws IOException {
                inputStream.close();
                outputStream.close();
            }
        };
    }

    @Override
    public boolean canConnect() {
        return true;
    }
}
