package mt.games.qwirkle.network.client;

import mt.games.qwirkle.network.IConnectCallbacks;
import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.StreamConfig;

import java.io.*;

public class ClientStreamConnector implements IConnector<StreamConfig> {

    @Override
    public IConnection apply(StreamConfig o, IConnectCallbacks<StreamConfig> connectCallbacks) {
        PipedInputStream streamIn = new PipedInputStream();
        PipedOutputStream streamOut = new PipedOutputStream();
        try {
            o.connectTo(streamIn, streamOut);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalStateException e) {
            throw new RuntimeException("Cannot connect to already used StreamConfig!", e);
        }
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
