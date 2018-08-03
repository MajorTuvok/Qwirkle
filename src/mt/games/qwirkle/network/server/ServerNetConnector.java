package mt.games.qwirkle.network.server;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import mt.games.qwirkle.network.IConnectCallbacks;
import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.SimpleSocketConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetConnector implements IConnector<ServerInetSocketConfig> {

    @Override
    @Nullable
    public IConnection apply(@NotNull ServerInetSocketConfig arg, @NotNull IConnectCallbacks<ServerInetSocketConfig> connectCallbacks) {
        try {
            connectCallbacks.onPrepareConnect(this);

            ServerSocket serverSocket = setupServer(arg);

            connectCallbacks.onTryConnect(this);

            Socket socket = performConnect(arg, serverSocket);

            connectCallbacks.onConnected(this);
            return new SimpleSocketConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
            connectCallbacks.onConnectFailed(this, e);
        }
        return null;
    }

    @Override
    public boolean canConnect() {
        return true;
    }

    private ServerSocket setupServer(@NotNull ServerInetSocketConfig arg) throws IOException {
        ServerSocket serverSocket = createServer(arg);
        serverSocket.setReuseAddress(arg.isReuseAddress());
        if (arg.getSoTimeout() > 0) {
            serverSocket.setSoTimeout(arg.getSoTimeout());
        }
        if (arg.getBandwith() != Integer.MIN_VALUE && arg.getConnectionTime() != Integer.MIN_VALUE && arg.getLatency() != Integer.MIN_VALUE) {
            serverSocket.setPerformancePreferences(arg.getBandwith(), arg.getConnectionTime(), arg.getLatency());
        }
        if (arg.getReceiveBufferSize() > 0) {
            serverSocket.setReceiveBufferSize(arg.getReceiveBufferSize());
        }
        return serverSocket;
    }

    private ServerSocket createServer(@NotNull ServerInetSocketConfig arg) throws IOException {
        if (arg.getBacklog() > 0 && arg.getBindAddress() != null) {
            return new ServerSocket(arg.getPort(), arg.getBacklog(), arg.getBindAddress());
        } else if (arg.getBacklog() > 0 && arg.getBindAddress() == null) {
            return new ServerSocket(arg.getPort(), arg.getBacklog());
        } else {
            return new ServerSocket(arg.getPort());
        }
    }

    private Socket performConnect(@NotNull ServerInetSocketConfig arg, @NotNull ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        socket.setOOBInline(arg.isOOBInLine());
        if (arg.getSendBufferSize() > 0) {
            socket.setSendBufferSize(arg.getSendBufferSize());
        }
        return socket;
    }
}
