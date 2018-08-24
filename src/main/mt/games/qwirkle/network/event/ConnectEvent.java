package mt.games.qwirkle.network.event;

import com.google.auto.value.AutoValue;
import mt.games.qwirkle.network.ConnectionManager;
import mt.games.qwirkle.network.IConnection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
@AutoValue.CopyAnnotations
public abstract class ConnectEvent<T, V> {
    @Nonnull
    abstract ConnectionManager<T, V> getManager();

    @Nonnull
    abstract T getConfigObject();

    @AutoValue
    @AutoValue.CopyAnnotations
    @Immutable
    public static abstract class PrepareConnectEvent<T, V> extends ConnectEvent<T, V> {
        @Nonnull
        public static <CONF, DAT> PrepareConnectEvent<CONF, DAT> newInstance(@Nonnull final ConnectionManager<CONF, DAT> manager, @Nonnull CONF configObject) {
            return new AutoValue_ConnectEvent_PrepareConnectEvent<>(manager, configObject);
        }
    }

    @AutoValue
    @AutoValue.CopyAnnotations
    @Immutable
    public static abstract class TryConnectEvent<T, V> extends ConnectEvent<T, V> {
        @Nonnull
        public static <CONF, DAT> TryConnectEvent<CONF, DAT> newInstance(@Nonnull final ConnectionManager<CONF, DAT> manager, @Nonnull final CONF configObject) {
            return new AutoValue_ConnectEvent_TryConnectEvent<>(manager, configObject);
        }
    }

    @AutoValue
    public static abstract class ConnectedEvent<T, V> extends ConnectEvent<T, V> {
        private boolean allowConnection = true;

        @Nonnull
        public static <CONF, DAT> ConnectedEvent<CONF, DAT> newInstance(@Nonnull final ConnectionManager<CONF, DAT> manager, @Nonnull final CONF configObject, @Nonnull final IConnection connection) {
            return new AutoValue_ConnectEvent_ConnectedEvent<>(manager, configObject, connection);
        }

        public void setAllowConnection(boolean allowConnection) {
            this.allowConnection = allowConnection;
        }

        @Nonnull
        abstract IConnection getEstablishedConnection();

        public boolean allowConnection() {
            return allowConnection;
        }
    }

    @AutoValue
    public static abstract class ConnectFailedEvent<T, V> extends ConnectEvent<T, V> {
        private boolean mRethrow;

        ConnectFailedEvent() {
            mRethrow = false;
        }

        @Nonnull
        public static <CONF, DAT> ConnectFailedEvent<CONF, DAT> newInstance(@Nonnull final ConnectionManager<CONF, DAT> manager, @Nonnull final CONF configObject, @Nullable final Exception exception) {
            return new AutoValue_ConnectEvent_ConnectFailedEvent<>(manager, configObject, exception);
        }

        public void setRethrow(boolean rethrow) {
            mRethrow = rethrow;
        }

        @Nullable
        abstract Exception getFailureReason();

        public boolean rethrow() {
            return mRethrow;
        }
    }
}
