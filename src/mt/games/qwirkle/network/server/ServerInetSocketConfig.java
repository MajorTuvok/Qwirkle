package mt.games.qwirkle.network.server;

import com.sun.istack.internal.Nullable;
import mt.games.qwirkle.network.InetSocketConfig;

import java.net.InetAddress;

public class ServerInetSocketConfig extends InetSocketConfig {
    private final int mBacklog;
    private final InetAddress mBindAddress;

    public ServerInetSocketConfig(int port) {
        super(port);
        mBacklog = -1;
        mBindAddress = null;
    }

    public ServerInetSocketConfig(int port, int receiveBufferSize, int sendBufferSize, int connectionTime, int latency, int bandwith, boolean OOBInLine, boolean reuseAddress, int soTimeout, int backlog, InetAddress bindAddress) {
        super(port, receiveBufferSize, sendBufferSize, connectionTime, latency, bandwith, OOBInLine, reuseAddress, soTimeout);
        mBacklog = backlog;
        mBindAddress = bindAddress;
    }

    public int getBacklog() {
        return mBacklog;
    }

    @Nullable
    public InetAddress getBindAddress() {
        return mBindAddress;
    }

    public static class Builder extends InetSocketConfig.Builder {
        private int mBacklog;
        private InetAddress mBindAddress;

        public Builder(int port) {
            super(port);
            mBacklog = -1;
            mBindAddress = null;
        }

        public int getBacklog() {
            return mBacklog;
        }

        public Builder setBacklog(int backlog) {
            mBacklog = backlog;
            return this;
        }

        public InetAddress getBindAddress() {
            return mBindAddress;
        }

        public Builder setBindAddress(@Nullable InetAddress bindAddress) {
            mBindAddress = bindAddress;
            return this;
        }

        @Override
        public ServerInetSocketConfig create() {
            return new ServerInetSocketConfig(getPort(), getReceiveBufferSize(), getSendBufferSize(), getConnectionTime(), getLatency(), getBandwidth(), isOOBInLine(), isReuseAddress(), getSoTimeout(), getBacklog(), getBindAddress());
        }
    }
}
