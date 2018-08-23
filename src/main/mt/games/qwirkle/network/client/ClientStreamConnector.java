package mt.games.qwirkle.network.client;


import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.StreamConfig;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;

public class ClientStreamConnector implements IConnector<StreamConfig, Object> {

    @Override
    public Object prepareConnect(StreamConfig streamConfig) {
        return null;
    }

    @Nullable
    @Override
    public IConnection tryConnect(@Nonnull StreamConfig streamConfig, Object preparedData) throws IOException {
        PipedInputStream streamIn = new PipedInputStream();
        PipedOutputStream streamOut = new PipedOutputStream();
        streamConfig.connectTo(streamIn, streamOut);
        return new IConnection() {
            @Override
            @Nonnull
            public InputStream getInputStream() {
                return streamIn;
            }

            @Override
            @Nonnull
            public OutputStream getOutputStream() {
                return streamOut;
            }

            @Override
            public void close() throws IOException {
                streamIn.close();
                streamOut.close();
            }
        };
    }

    @Override
    public boolean canConnect() {
        return true;
    }
}
