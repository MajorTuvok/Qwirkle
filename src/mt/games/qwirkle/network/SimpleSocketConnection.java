package mt.games.qwirkle.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class SimpleSocketConnection implements IConnection {
    private InputStream mInputStream;
    private InputStream mOriginIn;
    private OutputStream mOriginOut;
    private Socket mOriginSocket;
    private OutputStream mOutputStream;

    public SimpleSocketConnection(Socket socket) throws IOException {
        mOriginSocket = Objects.requireNonNull(socket);
        mOriginIn = mOriginSocket.getInputStream();
        mOriginOut = mOriginSocket.getOutputStream();
        mInputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                return mOriginIn.read();
            }

            @Override
            public int read(byte[] b) throws IOException {
                return mOriginIn.read(b);
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                return mOriginIn.read(b, off, len);
            }

            @Override
            public long skip(long n) throws IOException {
                return mOriginIn.skip(n);
            }

            @Override
            public int available() throws IOException {
                return mOriginIn.available();
            }

            @Override
            public void close() throws IOException {
                SimpleSocketConnection.this.close();
            }

            @Override
            public synchronized void mark(int readlimit) {
                mOriginIn.mark(readlimit);
            }

            @Override
            public synchronized void reset() throws IOException {
                mOriginIn.reset();
            }

            @Override
            public boolean markSupported() {
                return mOriginIn.markSupported();
            }
        };
        mOutputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                mOriginOut.write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                mOriginOut.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                mOriginOut.write(b, off, len);
            }

            @Override
            public void flush() throws IOException {
                mOriginOut.flush();
            }

            @Override
            public void close() throws IOException {
                SimpleSocketConnection.this.close();
            }
        };
    }

    @Override
    public InputStream getInputStream() {
        return mInputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        return mOutputStream;
    }

    public Socket getOriginSocket() {
        return mOriginSocket;
    }

    protected InputStream getOriginIn() {
        return mOriginIn;
    }

    protected OutputStream getOriginOut() {
        return mOriginOut;
    }

    @Override
    public void close() throws IOException {
        getOriginSocket().close(); //closes the backing inputStreams as well
    }
}
