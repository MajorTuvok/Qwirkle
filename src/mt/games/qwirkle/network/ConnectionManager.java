package mt.games.qwirkle.network;

import com.sun.istack.internal.Nullable;
import mt.games.qwirkle.network.concurrent.ICrossThreadConnectCallbacks;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ConnectionManager<T> implements IConnectCallbacks<T> {
    public static enum State {
        NOT_CALLED(true),
        PREPARING,
        CONNECTING,
        CONNECTED,
        FAILED(true);
        private boolean mAllowReconnect;

        State() {
            this(false);
        }

        State(boolean allowReconnect) {
            mAllowReconnect = allowReconnect;
        }

        public boolean allowConnect() {
            return mAllowReconnect;
        }
    }

    private Executor mCallbackDeliverer;
    private List<IConnectCallbacks<T>> mConnectCallbacks;
    private State mConnectionState;
    private IConnector<T> mConnector;
    private CompletableFuture<IConnection> mPendingConnection;
    private List<ICrossThreadConnectCallbacks<T>> mSelfDeliveredCallbacks;
    private Executor mWorker;

    public State getConnectionState() {
        return mConnectionState;
    }

    protected void setConnectionState(State connectionState) {
        mConnectionState = connectionState;
    }

    public void addConnectCallbacks(IConnectCallbacks<T> connectCallbacks) {
        mConnectCallbacks.add(connectCallbacks);
    }

    public boolean removeConnectCallbacks(IConnectCallbacks<T> connectCallbacks) {
        return mConnectCallbacks.remove(connectCallbacks);
    }

    public void addSelfDeliveredConnectCallbacks(ICrossThreadConnectCallbacks<T> connectCallbacks) {
        mSelfDeliveredCallbacks.add(connectCallbacks);
    }

    public boolean removeSelfDeliveredConnectCallbacks(ICrossThreadConnectCallbacks<T> connectCallbacks) {
        return mSelfDeliveredCallbacks.remove(connectCallbacks);
    }

    public boolean connect(final T data) {
        if (mConnectionState.allowConnect() && mConnector.canConnect()) {
            setConnectionState(State.NOT_CALLED);
            mPendingConnection =
                    CompletableFuture.supplyAsync(() -> mConnector.apply(data, ConnectionManager.this), mWorker);
            mPendingConnection.thenAccept(this::onComplete);
        }
        return false;
    }

    @Override
    public void onPrepareConnect(IConnector<T> connector) {
        setConnectionState(State.PREPARING);
        if (!mConnectCallbacks.isEmpty()) {
            mCallbackDeliverer.execute(() -> {
                for (IConnectCallbacks<T> callback : mConnectCallbacks) {
                    callback.onPrepareConnect(connector);
                }
            });
        }
        for (ICrossThreadConnectCallbacks<T> callback : mSelfDeliveredCallbacks) {
            callback.getTargetExecutor().execute(() -> callback.onPrepareConnect(connector));
        }
    }

    @Override
    public void onTryConnect(IConnector<T> connector) {
        setConnectionState(State.CONNECTING);
        if (!mConnectCallbacks.isEmpty()) {
            mCallbackDeliverer.execute(() -> {
                for (IConnectCallbacks<T> callback : mConnectCallbacks) {
                    callback.onTryConnect(connector);
                }
            });
        }
        for (ICrossThreadConnectCallbacks<T> callback : mSelfDeliveredCallbacks) {
            callback.getTargetExecutor().execute(() -> callback.onTryConnect(connector));
        }
    }

    @Override
    public void onConnected(IConnector<T> connector) {
        setConnectionState(State.CONNECTED);
        if (!mConnectCallbacks.isEmpty()) {
            mCallbackDeliverer.execute(() -> {
                for (IConnectCallbacks<T> callback : mConnectCallbacks) {
                    callback.onConnected(connector);
                }
            });
        }
        for (ICrossThreadConnectCallbacks<T> callback : mSelfDeliveredCallbacks) {
            callback.getTargetExecutor().execute(() -> callback.onConnected(connector));
        }
    }

    @Override
    public void onConnectFailed(IConnector<T> connector, @Nullable Exception cause) {
        setConnectionState(State.FAILED);
        if (!mConnectCallbacks.isEmpty()) {
            mCallbackDeliverer.execute(() -> {
                for (IConnectCallbacks<T> callback : mConnectCallbacks) {
                    callback.onConnectFailed(connector, cause);
                }
            });
        }
        for (ICrossThreadConnectCallbacks<T> callback : mSelfDeliveredCallbacks) {
            callback.getTargetExecutor().execute(() -> callback.onConnectFailed(connector, cause));
        }
    }

    protected void onComplete(IConnection connection) {
        if (mPendingConnection != null) {
            mPendingConnection = null;
        }

    }
}
