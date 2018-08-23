package mt.games.qwirkle.network.concurrent;

import java.util.concurrent.Executor;

public enum SynchronousExecutor implements Executor {
    INSTANCE;

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
