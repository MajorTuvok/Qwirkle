package mt.games.qwirkle.network;


import com.google.common.eventbus.EventBus;
import mt.games.qwirkle.network.event.ConnectEvent.ConnectFailedEvent;
import mt.games.qwirkle.network.event.ConnectEvent.ConnectedEvent;
import mt.games.qwirkle.network.event.ConnectEvent.PrepareConnectEvent;
import mt.games.qwirkle.network.event.ConnectEvent.TryConnectEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ConnectionManager<T, V> {
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

    private final IConnector<T, V> mConnector = null;
    private Executor mCallbackDeliverer;
    private State mConnectionState;
    private EventBus mEventBus;
    private CompletableFuture<IConnection> mPendingConnection;
    private Executor mWorker;

    public EventBus getEventBus() {
        return mEventBus;
    }

    public State getConnectionState() {
        return mConnectionState;
    }

    protected void setConnectionState(State connectionState) {
        mConnectionState = connectionState;
    }


    public boolean connect(final T data) {
        if (mConnectionState.allowConnect() && mConnector.canConnect()) {
            setConnectionState(State.NOT_CALLED);
            mPendingConnection =
                    CompletableFuture.supplyAsync(() -> {
                                mEventBus.post(PrepareConnectEvent.newInstance(ConnectionManager.this, data));
                                V connectData = null;
                                try {
                                    synchronized (mConnector) {
                                        connectData = mConnector.prepareConnect(data);
                                    }
                                } catch (Exception e) {
                                    handleConnectFailed(e, data);
                                    return null;
                                }
                                mEventBus.post(TryConnectEvent.newInstance(ConnectionManager.this, data));
                                IConnection connection = null;
                                try {
                                    synchronized (mConnector) {
                                        connection = mConnector.tryConnect(data, connectData);
                                    }
                                } catch (Exception e) {
                                    handleConnectFailed(e, data);
                                }
                                if (connection != null) {
                                    connection = handleConnected(connection, data);
                                }
                                return connection;
                            }
                            , mWorker);
            mPendingConnection.thenAccept(this::onComplete);
        }
        return false;
    }

    protected void onComplete(IConnection connection) {
        if (mPendingConnection != null) {
            mPendingConnection = null;
        }
        if (getConnectionState() != State.CONNECTED) return;
    }

    private void handleConnectFailed(@Nullable Exception e, @Nonnull T data) {
        ConnectFailedEvent<T, V> event = ConnectFailedEvent.newInstance(ConnectionManager.this, data, e);
        mEventBus.post(event);
        if (e != null) {
            if (event.rethrow()) {
                throw new RuntimeException("Failed to perform connect!", e);
            }
            e.printStackTrace();
        }
        setConnectionState(State.FAILED);
    }

    private IConnection handleConnected(@Nonnull IConnection connection, @Nonnull T data) {
        setConnectionState(State.CONNECTED);
        ConnectedEvent<T, V> event = ConnectedEvent.newInstance(ConnectionManager.this, data, connection);
        mEventBus.post(event);
        if (!event.allowConnection()) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection = null;
            handleConnectFailed(null, data);
        }
        return connection;
    }
}
