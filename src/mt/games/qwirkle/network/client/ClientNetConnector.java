package mt.games.qwirkle.network.client;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import mt.games.qwirkle.network.IConnectCallbacks;
import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.SimpleSocketConnection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNetConnector implements IConnector<ClientInetSocketConfig> {
    @Override
    @Nullable
    public IConnection apply(@NotNull ClientInetSocketConfig arg, @NotNull IConnectCallbacks<ClientInetSocketConfig> callbacks) {
        try {
            callbacks.onPrepareConnect(this);

            Socket socket = new Socket();
            setupSocket(arg, socket);

            callbacks.onTryConnect(this);

            socket.connect(new InetSocketAddress(InetAddress.getByName(arg.getName()), arg.getPort()));

            callbacks.onConnected(this);

            return new SimpleSocketConnection(socket);
        } catch (UnknownHostException hostE) {
            System.err.println("Failed to resolve Host Address!");
            hostE.printStackTrace();
            callbacks.onConnectFailed(this, hostE);
        } catch (IOException e) {
            e.printStackTrace();
            callbacks.onConnectFailed(this, e);
        }
        return null;
    }

    @Override
    public boolean canConnect() {
        return true;
    }

    private void setupSocket(@NotNull ClientInetSocketConfig arg, @NotNull Socket socket) throws IOException {
        socket.setReuseAddress(arg.isReuseAddress());
        socket.setOOBInline(arg.isOOBInLine());
        if (arg.getBandwith() != Integer.MIN_VALUE && arg.getConnectionTime() != Integer.MIN_VALUE && arg.getLatency() != Integer.MIN_VALUE) {
            socket.setPerformancePreferences(arg.getConnectionTime(), arg.getLatency(), arg.getBandwith());
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
    }

}
