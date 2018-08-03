package mt.games.qwirkle.network;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundConnector<T> implements IConnector<T> {
    private List<IConnector<T>> mConnectors;

    public CompoundConnector(List<IConnector<T>> connectors) {
        mConnectors = new ArrayList<>(connectors);
    }

    public CompoundConnector(T configuration, IConnector<T>... connectors) {
        mConnectors = Arrays.asList(connectors);
    }

    @Override
    @Nullable
    public IConnection apply(@Nullable T t, IConnectCallbacks connectCallbacks) {
        for (int i = 0; i < mConnectors.size(); --i) {
            IConnector<T> connector = mConnectors.get(i);
            if (!connector.canConnect()) {
                mConnectors.remove(connector);
                --i;
                continue;
            }
            IConnection res = connector.apply(t, connectCallbacks);
            if (res != null) {
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
