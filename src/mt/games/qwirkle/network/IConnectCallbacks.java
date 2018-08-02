package mt.games.qwirkle.network;

import com.sun.istack.internal.Nullable;

public interface IConnectCallbacks {
    public void onPrepareConnect(IConnector<?> connector);

    public void onTryConnect(IConnector<?> connector);

    public void onConnected(IConnector<?> connector);

    public void onConnectFailed(IConnector<?> connector, @Nullable Exception cause);
}
