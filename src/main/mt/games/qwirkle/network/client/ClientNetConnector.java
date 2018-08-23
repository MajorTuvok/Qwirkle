package mt.games.qwirkle.network.client;


import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.SimpleSocketConnection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientNetConnector implements IConnector<ClientInetSocketConfig, Socket> {

    @Override
    public Socket prepareConnect(ClientInetSocketConfig clientInetSocketConfig) throws IOException {
        return setupSocket(clientInetSocketConfig);
    }

    @Nullable
    @Override
    public IConnection tryConnect(@Nonnull ClientInetSocketConfig clientInetSocketConfig, Socket socket) throws IOException {
        socket.connect(new InetSocketAddress(InetAddress.getByName(clientInetSocketConfig.getName()), clientInetSocketConfig.getPort()));
        return new SimpleSocketConnection(socket);
    }

    @Override
    public boolean canConnect() {
        return true;
    }

    private Socket setupSocket(@Nonnull ClientInetSocketConfig arg) throws IOException {
        Socket socket = new Socket();
        socket.setReuseAddress(arg.isReuseAddress());
        socket.setOOBInline(arg.isOOBInLine());
        if (arg.getBandwidth() != Integer.MIN_VALUE && arg.getConnectionTime() != Integer.MIN_VALUE && arg.getLatency() != Integer.MIN_VALUE) {
            socket.setPerformancePreferences(arg.getConnectionTime(), arg.getLatency(), arg.getBandwidth());
        }
        if (arg.getSoTimeout() > 0) {
            socket.setSoTimeout(arg.getSoTimeout());
        }
        if (arg.getReceiveBufferSize() > 0) {
            socket.setReceiveBufferSize(arg.getReceiveBufferSize());
        }
        if (arg.getSendBufferSize() > 0) {
            socket.setSendBufferSize(arg.getSendBufferSize());
        }
        return socket;
    }

}
