package mt.games.qwirkle.network.client;

import mt.games.qwirkle.network.InetSocketConfig;

import java.util.Objects;

public class ClientInetSocketConfig extends InetSocketConfig {
    private final String mName;

    public ClientInetSocketConfig(String name, int port) {
        super(port);
        mName = name;
    }

    private ClientInetSocketConfig(int port, int receiveBufferSize, int sendBufferSize, int connectionTime, int latency, int bandwith, boolean OOBInLine, boolean reuseAddress, int soTimeout, String name) {
        super(port, receiveBufferSize, sendBufferSize, connectionTime, latency, bandwith, OOBInLine, reuseAddress, soTimeout);
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public static class Builder extends InetSocketConfig.Builder {
        private String mName;

        public Builder(String name, int port) {
            super(port);
            setName(name);
        }

        public String getName() {
            return mName;
        }

        public Builder setName(String name) {
            mName = Objects.requireNonNull(name);
            return this;
        }

        public ClientInetSocketConfig create() {
            return new ClientInetSocketConfig(getPort(), getReceiveBufferSize(), getSendBufferSize(), getConnectionTime(), getLatency(), getBandwidth(), isOOBInLine(), isReuseAddress(), getSoTimeout(), getName());
        }
    }
}
