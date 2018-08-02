package mt.games.qwirkle.network;


public abstract class InetSocketConfig {
    private final int mBandwith;
    private final int mConnectionTime;
    private final int mLatency;
    private final boolean mOOBInLine;
    private final int mPort;
    private final int mReceiveBufferSize;
    private final boolean mReuseAddress;
    private final int mSendBufferSize;
    private final int mSoTimeout;

    public InetSocketConfig(int port) {
        this(port, -1, -1, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, false, false, -1);
    }

    protected InetSocketConfig(int port, int receiveBufferSize, int sendBufferSize, int connectionTime, int latency, int bandwith, boolean OOBInLine, boolean reuseAddress, int soTimeout) {
        mPort = port;
        mReceiveBufferSize = receiveBufferSize;
        mSendBufferSize = sendBufferSize;
        mConnectionTime = connectionTime;
        mLatency = latency;
        mBandwith = bandwith;
        mOOBInLine = OOBInLine;
        mReuseAddress = reuseAddress;
        mSoTimeout = soTimeout;
    }

    public int getPort() {
        return mPort;
    }

    public int getReceiveBufferSize() {
        return mReceiveBufferSize;
    }

    public int getSendBufferSize() {
        return mSendBufferSize;
    }

    public int getConnectionTime() {
        return mConnectionTime;
    }

    public int getLatency() {
        return mLatency;
    }

    public int getBandwith() {
        return mBandwith;
    }

    public boolean isOOBInLine() {
        return mOOBInLine;
    }

    public boolean isReuseAddress() {
        return mReuseAddress;
    }

    public int getSoTimeout() {
        return mSoTimeout;
    }

    @Override
    public int hashCode() {
        int result = getReceiveBufferSize();
        result = 31 * result + getSendBufferSize();
        result = 31 * result + getConnectionTime();
        result = 31 * result + getLatency();
        result = 31 * result + getBandwith();
        result = 31 * result + (isOOBInLine() ? 1 : 0);
        result = 31 * result + (mReuseAddress ? 1 : 0);
        result = 31 * result + mSoTimeout;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InetSocketConfig)) return false;

        InetSocketConfig that = (InetSocketConfig) o;

        if (getReceiveBufferSize() != that.getReceiveBufferSize()) return false;
        if (getSendBufferSize() != that.getSendBufferSize()) return false;
        if (getConnectionTime() != that.getConnectionTime()) return false;
        if (getLatency() != that.getLatency()) return false;
        if (getBandwith() != that.getBandwith()) return false;
        if (isOOBInLine() != that.isOOBInLine()) return false;
        if (mReuseAddress != that.mReuseAddress) return false;
        return mSoTimeout == that.mSoTimeout;
    }

    @Override
    public String toString() {
        return "InetSocketConfig{" +
                "receiveBufferSize=" + mReceiveBufferSize +
                ", sendBufferSize=" + mSendBufferSize +
                ", connectionTime=" + mConnectionTime +
                ", latency=" + mLatency +
                ", bandwith=" + mBandwith +
                ", oOBInLine=" + mOOBInLine +
                ", teuseAddress=" + mReuseAddress +
                ", doTimeout=" + mSoTimeout +
                '}';
    }

    public static abstract class Builder {
        private int mBandwidth;
        private int mConnectionTime;
        private int mLatency;
        private boolean mOOBInLine;
        private int mPort;
        private int mReceiveBufferSize;
        private boolean mReuseAddress;
        private int mSendBufferSize;
        private int mSoTimeout;

        public Builder(int port) {
            setPort(port);
            mReceiveBufferSize = -1;
            mSendBufferSize = -1;
            mConnectionTime = Integer.MIN_VALUE;
            mLatency = Integer.MIN_VALUE;
            mBandwidth = Integer.MIN_VALUE;
            mOOBInLine = false;
            mReuseAddress = false;
            mSoTimeout = -1;
        }

        public int getPort() {
            return mPort;
        }

        public Builder setPort(int port) {
            if (port < 0 || port > 65535) {
                throw new IllegalArgumentException("Cannot have port Number " + port + "!");
            }
            mPort = port;
            return this;
        }

        public int getReceiveBufferSize() {
            return mReceiveBufferSize;
        }

        public Builder setReceiveBufferSize(int receiveBufferSize) {
            mReceiveBufferSize = receiveBufferSize;
            return this;
        }

        public int getSendBufferSize() {
            return mSendBufferSize;
        }

        public Builder setSendBufferSize(int sendBufferSize) {
            mSendBufferSize = sendBufferSize;
            return this;
        }

        public int getConnectionTime() {
            return mConnectionTime;
        }

        public int getLatency() {
            return mLatency;
        }

        public int getBandwidth() {
            return mBandwidth;
        }

        public boolean isOOBInLine() {
            return mOOBInLine;
        }

        public Builder setOOBInLine(boolean OOBInLine) {
            mOOBInLine = OOBInLine;
            return this;
        }

        public boolean isReuseAddress() {
            return mReuseAddress;
        }

        public Builder setReuseAddress(boolean reuseAddress) {
            mReuseAddress = reuseAddress;
            return this;
        }

        public int getSoTimeout() {
            return mSoTimeout;
        }

        public Builder setSoTimeout(int soTimeout) {
            mSoTimeout = soTimeout;
            return this;
        }

        public Builder setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
            this.mConnectionTime = connectionTime;
            this.mLatency = latency;
            this.mBandwidth = bandwidth;
            return this;
        }

        public abstract InetSocketConfig create();
    }
}
