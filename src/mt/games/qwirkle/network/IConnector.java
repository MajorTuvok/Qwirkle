package mt.games.qwirkle.network;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.function.BiFunction;

public interface IConnector<T> extends BiFunction<T, IConnectCallbacks<T>, IConnection> {

    /**
     * @param t                Data passed to this Connector, which describes the way the connection should be established.
     * @param connectCallbacks The callbacks to inform about connect Progress. All Callbacks have to be called in order.
     * @return An InputStream allowing Data Transfer via the connection
     * @throws UnsupportedOperationException if {@link #canConnect} would return true because no further connection can be created.
     */
    @Override
    @Nullable
    public IConnection apply(@NotNull T t, @NotNull IConnectCallbacks<T> connectCallbacks);

    /**
     * @return whether or not this Connector can connect again
     */
    public boolean canConnect();
}
