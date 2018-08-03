package mt.games.qwirkle.backend;

import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;

import java.util.ArrayList;
import java.util.List;

public class ServerGame {
    private ConnectionManager<?> mConnectionManager;

    public <T> ServerGame(IConnector<T> connector, T configuration) {
        mConnectionManager = new ConnectionManager<>(connector, configuration);
    }

    private static class ConnectionManager<T> {
        private List<IConnection> mActiveConnections;
        private T mConnectionConfiguration;
        private IConnector<T> mConnector;

        ConnectionManager(IConnector<T> connector, T connectionConfiguration) {
            mConnector = connector;
            mConnectionConfiguration = connectionConfiguration;
            mActiveConnections = new ArrayList<>();
        }

        void startConnecting() {//TODO make this async
            //Doing this on the calling Thread may work for now, but this will almost certainly impact Performance

        }
    }
}
