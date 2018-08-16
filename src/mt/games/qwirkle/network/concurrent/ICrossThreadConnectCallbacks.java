package mt.games.qwirkle.network.concurrent;

import mt.games.qwirkle.network.IConnectCallbacks;

import java.util.concurrent.Executor;

public interface ICrossThreadConnectCallbacks<T> extends IConnectCallbacks<T> {
    public default Executor getTargetExecutor() {
        return SynchronousExecutor.INSTANCE;
    }
}
