package mt.games.qwirkle.network;

import mt.games.qwirkle.network.event.ConnectEvent.ConnectFailedEvent;
import mt.games.qwirkle.network.event.ConnectEvent.ConnectedEvent;
import mt.games.qwirkle.network.event.ConnectEvent.PrepareConnectEvent;
import mt.games.qwirkle.network.event.ConnectEvent.TryConnectEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IConnector<T, D> {

    /**
     * Perform any setups you may need here.
     * Called after {@link PrepareConnectEvent} was posted on the EventBus and before {@link TryConnectEvent} is posted.
     * Because of eventual Async processing of the Events, listeners may not necessarily have received the {@link PrepareConnectEvent} yet.
     * @param t Data passed to this Connector, which describes the way the connection should be established.
     * @throws Exception on failure
     * @return Additional prepared data for {@link #tryConnect(Object, Object)}
     */
    public D prepareConnect(T t) throws Exception;

    /**
     * Perform the actual connect. Setup should have been done in {@link #prepareConnect(Object)}.
     * Called after {@link TryConnectEvent} was posted on the EventBus and before {@link ConnectedEvent} or {@link ConnectFailedEvent} is posted.
     * Because of eventual Async processing of the Events, listeners may not necessarily have received the {@link PrepareConnectEvent} yet.
     * <br>
     * <strong>Throwing or returning null will deem this this connect attempt to be failed and result in an {@link ConnectFailedEvent} to be posted instead of an {@link ConnectedEvent}</strong>
     *
     * @param t            Data passed to this Connector, which describes the way the connection should be established.
     * @param preparedData The data that was previously created in {{@link #prepareConnect(Object)}}
     * @return An {@link IConnection} allowing Data Transfer via the connection. Returning null from here will deem this connect attempt as failed.
     * @throws Exception on any failure. Throwing will deem this Connect attempt as failed.
     */
    @Nullable
    public IConnection tryConnect(@Nonnull T t, D preparedData) throws Exception;

    /**
     * @return whether or not this Connector can connect again
     */
    public boolean canConnect();
}
