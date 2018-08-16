package mt.games.qwirkle.network.client;

import mt.games.qwirkle.network.IConnectCallbacks;
import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.StreamConfig;

import java.io.*;

public class ClientStreamConnector implements IConnector<StreamConfig> {

    @Override
    public IConnection apply(StreamConfig o, IConnectCallbacks<StreamConfig> connectCallbacks) {
        connectCallbacks.onPrepareConnect(this);
        PipedInputStream streamIn = new PipedInputStream();
        PipedOutputStream streamOut = new PipedOutputStream();
        connectCallbacks.onTryConnect(this);
        try {
            o.connectTo(streamIn, streamOut);
        } catch (IOException e) {
            connectCallbacks.onConnectFailed(this, e);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            connectCallbacks.onConnectFailed(this, e);
            throw new RuntimeException("Cannot connect to already used StreamConfig!", e);
        }
        connectCallbacks.onConnected(this);
        return new IConnection() {
            @Override
            public InputStream getInputStream() {
                return streamIn;
            }

            @Override
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
