package org.expensive.common.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketUtil {

    private Socket socket;
    private final int defaultTimeOut = 50000;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void openSocket(String host, int port) throws IOException {
        socket.connect(new InetSocketAddress(host, port), defaultTimeOut);
    }

    public void openSocketWithTime(String host, int port, int timeout) throws IOException {
        if (timeout <= 0) timeout = defaultTimeOut;
        socket.connect(new InetSocketAddress(host, port), timeout);
    }

}
