package mt.games.qwirkle.network.client;


import com.google.auto.value.AutoValue;
import com.google.auto.value.AutoValue.CopyAnnotations;
import mt.games.qwirkle.network.InetSocketConfig;


@AutoValue
@CopyAnnotations
public abstract class ClientInetSocketConfig extends InetSocketConfig {

    public static ClientInetSocketConfig create(String name, int port) {
        return builder(name, port).build();
    }

    public static ClientInetSocketConfig.Builder builder(String name, int port) {
        return builder().setPort(port).setName(name);
    }

    public static ClientInetSocketConfig.Builder builder() {
        return new AutoValue_ClientInetSocketConfig.Builder()
                .setReceiveBufferSize(-1)
                .setSendBufferSize(-1)
                .setConnectionTime(Integer.MIN_VALUE)
                .setBandwidth(Integer.MIN_VALUE)
                .setLatency(Integer.MIN_VALUE)
                .setOOBInLine(false)
                .setReuseAddress(false)
                .setSoTimeout(-1);
    }

    public abstract String getName();

    @AutoValue.Builder
    @CopyAnnotations
    public static abstract class Builder extends InetSocketConfig.Builder<Builder> {

        abstract Builder setName(String newName);

        public ClientInetSocketConfig build() {
            validateValues();
            return autoBuild();
        }

        abstract ClientInetSocketConfig autoBuild();
    }
}
