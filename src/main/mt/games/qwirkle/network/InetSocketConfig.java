package mt.games.qwirkle.network;


import com.google.errorprone.annotations.Immutable;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@Immutable
@ThreadSafe
public abstract class InetSocketConfig {
    public abstract int getPort();

    public abstract int getReceiveBufferSize();

    public abstract int getSendBufferSize();

    public abstract int getConnectionTime();

    public abstract int getLatency();

    public abstract int getBandwidth();

    public abstract boolean isOOBInLine();

    public abstract boolean isReuseAddress();

    public abstract int getSoTimeout();

    @NotThreadSafe
    public abstract static class Builder<T extends Builder<T>> {
        public abstract T setPort(int newPort);

        protected abstract int getPort();

        public abstract T setReceiveBufferSize(int newReceiveBufferSize);

        public abstract T setSendBufferSize(int newSendBufferSize);

        public abstract T setConnectionTime(int newConnectionTime);

        public abstract T setLatency(int newLatency);

        public abstract T setBandwidth(int newBandwdith);

        public abstract T setOOBInLine(boolean newOOBInLine);

        public abstract T setReuseAddress(boolean newReuseAddress);

        public abstract T setSoTimeout(int newSoTimeout);

        public void validateValues() {
            if (getPort() < 0 || getPort() > 65535) {
                throw new IllegalArgumentException("Cannot have port Number " + getPort() + "! Valid range is [0,65535]!");
            }
        }
    }
}
