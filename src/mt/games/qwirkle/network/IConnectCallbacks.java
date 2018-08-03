package mt.games.qwirkle.network;

import com.sun.istack.internal.Nullable;

public interface IConnectCallbacks<T> {
    public void onPrepareConnect(IConnector<T> connector);

    public void onTryConnect(IConnector<T> connector);

    public void onConnected(IConnector<T> connector);

    public void onConnectFailed(IConnector<T> connector, @Nullable Exception cause);
}
