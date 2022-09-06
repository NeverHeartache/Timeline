package org.expensive.time;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketTest {
    public static void main(String[] args) {
        byte[] bytes = new byte[0];
        Socket socket = new Socket();
        SocketAddress sa = new InetSocketAddress("local.democracy.org", 8081);
        try {
            socket.connect(sa, 5000);
            InputStream in = socket.getInputStream();

            OutputStream socketOut = socket.getOutputStream();
            socketOut.flush();
            socketOut.write(bytes);
            OutputStream out = new ByteArrayOutputStream();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            System.out.println(inputStream.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
