package mt.games.qwirkle.network.client;


import com.google.auto.value.AutoValue;
import com.google.auto.value.AutoValue.CopyAnnotations;
import mt.games.qwirkle.network.InetSocketConfig;


@AutoValue
@CopyAnnotations
public abstract class ClientInetSocketConfig extends InetSocketConfig {

    public static Builder builder() {
        return new AutoValue_ClientInetSocketConfig.Builder();
    }

    public abstract String getName();

    @AutoValue.Builder
    @CopyAnnotations
    public static abstract class Builder extends InetSocketConfig.Builder<Builder> {

        public abstract Builder setName(String newName);

        public abstract ClientInetSocketConfig build();
    }
}
