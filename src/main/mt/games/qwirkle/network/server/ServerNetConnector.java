package mt.games.qwirkle.network.server;


import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.SimpleSocketConnection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetConnector implements IConnector<ServerInetSocketConfig, ServerSocket> {

    @Override
    public ServerSocket prepareConnect(ServerInetSocketConfig serverInetSocketConfig) throws IOException {
        return setupServer(serverInetSocketConfig);
    }

    @Nullable
    @Override
    public IConnection tryConnect(@Nonnull ServerInetSocketConfig serverInetSocketConfig, ServerSocket preparedData) throws Exception {
        Socket socket = performConnect(serverInetSocketConfig, preparedData);
        return new SimpleSocketConnection(socket);
    }

    @Override
    public boolean canConnect() {
        return true;
    }

    private ServerSocket setupServer(@Nonnull ServerInetSocketConfig arg) throws IOException {
        ServerSocket serverSocket = createServer(arg);
        serverSocket.setReuseAddress(arg.isReuseAddress());
        if (arg.getSoTimeout() > 0) {
            serverSocket.setSoTimeout(arg.getSoTimeout());
        }
        if (arg.getBandwidth() != Integer.MIN_VALUE && arg.getConnectionTime() != Integer.MIN_VALUE && arg.getLatency() != Integer.MIN_VALUE) {
            serverSocket.setPerformancePreferences(arg.getBandwidth(), arg.getConnectionTime(), arg.getLatency());
        }
        if (arg.getReceiveBufferSize() > 0) {
            serverSocket.setReceiveBufferSize(arg.getReceiveBufferSize());
        }
        return serverSocket;
    }

    private ServerSocket createServer(@Nonnull ServerInetSocketConfig arg) throws IOException {
        if (arg.getBacklog() > 0 && arg.getBindAddress() != null) {
            return new ServerSocket(arg.getPort(), arg.getBacklog(), arg.getBindAddress());
        } else if (arg.getBacklog() > 0 && arg.getBindAddress() == null) {
            return new ServerSocket(arg.getPort(), arg.getBacklog());
        } else {
            return new ServerSocket(arg.getPort());
        }
    }

    private Socket performConnect(@Nonnull ServerInetSocketConfig arg, @Nonnull ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        socket.setOOBInline(arg.isOOBInLine());
        if (arg.getSendBufferSize() > 0) {
            socket.setSendBufferSize(arg.getSendBufferSize());
        }
        return socket;
    }
}
