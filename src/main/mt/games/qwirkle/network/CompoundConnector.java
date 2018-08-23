package mt.games.qwirkle.network;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import mt.games.qwirkle.network.CompoundConnector.DataWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CompoundConnector<T, V> implements IConnector<T, DataWrapper<V>> {
    private boolean mConnectOnce;
    @Nonnull
    private List<IConnector<T, V>> mConnectors;
    private boolean mRemoveDisabled;

    public CompoundConnector(List<IConnector<T, V>> connectors) {
        mConnectors = new ArrayList<>(connectors);
        mConnectOnce = true;
        mRemoveDisabled = false;
    }

    public CompoundConnector(IConnector<T, V>... connectors) {
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
    public DataWrapper<V> prepareConnect(T t) throws Exception {
        for (int i = 0; i < mConnectors.size(); --i) {
            IConnector<T, V> connector = mConnectors.get(i);
            if (!connector.canConnect()) {
                if (isRemovingDisabled()) {
                    mConnectors.remove(connector);
                    --i;
                }
                continue;
            }
            V res = connector.prepareConnect(t);
            return DataWrapper.create(res, i);
        }
        return null;
    }

    @Nullable
    @Override
    public IConnection tryConnect(@Nonnull T t, DataWrapper<V> preparedData) throws Exception {
        Objects.requireNonNull(preparedData);
        int index = preparedData.getIndex();
        V data = preparedData.getData();
        return mConnectors.get(index).tryConnect(t, data);
    }

    @Override
    public boolean canConnect() {
        for (IConnector<T, V> connector : mConnectors) {
            if (connector.canConnect()) {
                return true;
            }
        }
        return false;
    }

    @AutoValue
    abstract static class DataWrapper<V> {
        public static <V> DataWrapper<V> create(@Nullable V newData, int newIndex) {
            Preconditions.checkArgument(newIndex >= 0, "List-Index may never be smaller than 0!");
            return new AutoValue_CompoundConnector_DataWrapper<>(newData, newIndex);
        }

        @Nullable
        abstract V getData();

        abstract int getIndex();
    }
}
