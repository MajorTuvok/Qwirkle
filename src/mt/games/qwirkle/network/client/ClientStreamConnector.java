package mt.games.qwirkle.network.client;

import mt.games.qwirkle.network.IConnectCallbacks;
import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;

public class ClientStreamConnector implements IConnector<Object> {

    @Override
    public IConnection apply(Object o, IConnectCallbacks connectCallbacks) {
        return null;
    }

    @Override
    public boolean canConnect() {
        return false;
    }
}
