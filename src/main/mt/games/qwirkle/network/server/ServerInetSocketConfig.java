package mt.games.qwirkle.network.server;

import com.google.auto.value.AutoValue;
import com.google.auto.value.AutoValue.CopyAnnotations;
import com.google.errorprone.annotations.Immutable;
import mt.games.qwirkle.network.InetSocketConfig;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.net.InetAddress;

@AutoValue
@CopyAnnotations
@Immutable
@ThreadSafe
public abstract class ServerInetSocketConfig extends InetSocketConfig {
    public static ServerInetSocketConfig create(int port) {
        return builder(port).build();
    }

    public static Builder builder(int port) {
        return builder().setPort(port);
    }

    public static Builder builder() {
        return new AutoValue_ServerInetSocketConfig.Builder()
                .setReceiveBufferSize(-1)
                .setSendBufferSize(-1)
                .setConnectionTime(Integer.MIN_VALUE)
                .setBandwidth(Integer.MIN_VALUE)
                .setLatency(Integer.MIN_VALUE)
                .setOOBInLine(false)
                .setReuseAddress(false)
                .setSoTimeout(-1)
                .setBacklog(-1);
    }

    public abstract int getBacklog();

    @Nullable
    public abstract InetAddress getBindAddress();

    @AutoValue.Builder
    @NotThreadSafe
    @CopyAnnotations
    public abstract static class Builder extends InetSocketConfig.Builder<Builder> {
        public abstract Builder setBacklog(int newBacklog);

        public abstract Builder setBindAddress(@Nullable InetAddress newBindAddress);

        public ServerInetSocketConfig build() {
            validateValues();
            return autoBuild();
        }

        abstract ServerInetSocketConfig autoBuild();
    }
}
