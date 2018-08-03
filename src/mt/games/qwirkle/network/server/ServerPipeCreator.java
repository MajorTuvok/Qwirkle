package mt.games.qwirkle.network.server;

import mt.games.qwirkle.network.StreamConfig;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.function.Supplier;

public enum ServerPipeCreator implements Supplier<StreamConfig> {
    STREAM_CONFIG_SUPPLIER;

    @Override
    public StreamConfig get() {
        return new StreamConfig(new PipedInputStream(), new PipedOutputStream());
    }
}
