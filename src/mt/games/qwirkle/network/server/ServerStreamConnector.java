package mt.games.qwirkle.network.server;

import mt.games.qwirkle.network.IConnectCallbacks;
import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.StreamConfig;

import java.io.*;

public class ServerStreamConnector implements IConnector<StreamConfig> {

    @Override
    public IConnection apply(StreamConfig streamConfig, IConnectCallbacks<StreamConfig> connectCallbacks) {
        connectCallbacks.onPrepareConnect(this);
        try {
            connectCallbacks.onTryConnect(this);
            PipedInputStream inputStream = streamConfig.getInputStream();
            PipedOutputStream outputStream = streamConfig.getOutputStream();
            connectCallbacks.onConnected(this);
            return new IConnection() {
                @Override
                public InputStream getInputStream() {
                    return inputStream;
                }

                @Override
                public OutputStream getOutputStream() {
                    return outputStream;
                }

                @Override
                public void close() throws IOException {
                    inputStream.close();
                    outputStream.close();
                }
            };
        } catch (Exception e) {
            connectCallbacks.onConnectFailed(this, e);
            return null;
        }

    }

    @Override
    public boolean canConnect() {
        return true;
    }
}
