package mt.games.qwirkle.network;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundConnector<T> implements IConnector<T> {
    private boolean mConnectOnce;
    @NotNull
    private List<IConnector<T>> mConnectors;
    private boolean mRemoveDisabled;

    public CompoundConnector(List<IConnector<T>> connectors) {
        mConnectors = new ArrayList<>(connectors);
        mConnectOnce = true;
        mRemoveDisabled = false;
    }

    public CompoundConnector(IConnector<T>... connectors) {
        mConnectors = Arrays.asList(connectors);
        mConnectOnce = true;
        mRemoveDisabled = false;
    }

    public void setConnectOnce(boolean connectOnce) {
        mConnectOnce = connectOnce;
    }

    public void setRemoveDisabled(boolean removeDisabled) {
        mRemoveDisabled = removeDisabled;
    }

    /**
     * @return whether every passed in connector may only ever connect once, regardless whether {@link IConnector#canConnect()} returns true or not.
     */
    public boolean isConnectingOnlyOnce() {
        return mConnectOnce;
    }

    /**
     * @return whether {@link IConnector}'s which state that they can't connect should be removed form the waiting list.
     */
    public boolean isRemovingDisabled() {
        return mRemoveDisabled;
    }

    @Override
    @Nullable
    public IConnection apply(@Nullable T t, IConnectCallbacks<T> connectCallbacks) {
        for (int i = 0; i < mConnectors.size(); --i) {
            IConnector<T> connector = mConnectors.get(i);
            if (!connector.canConnect()) {
                if (isRemovingDisabled()) {
                    mConnectors.remove(connector);
                    --i;
                }
                continue;
            }
            IConnection res = connector.apply(t, connectCallbacks);
            if (res != null) {
                if (isConnectingOnlyOnce()) {
                    mConnectors.remove(connector);
                    --i;
                }
                return res;
            }
        }
        return null;
    }

    @Override
    public boolean canConnect() {
        for (IConnector<T> connector : mConnectors) {
            if (connector.canConnect()) {
                return true;
            }
        }
        return false;
    }
}
