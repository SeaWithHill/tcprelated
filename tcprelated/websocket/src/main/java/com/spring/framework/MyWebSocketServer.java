package main.java.com.spring.framework;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class MyWebSocketServer extends WebSocketServer {

    public MyWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("客户端已连接: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("客户端已断开连接: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("收到消息: " + message);

        // 向客户端发送消息
        conn.send("服务器回复: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    public static void main(String[] args) {
        MyWebSocketServer server = new MyWebSocketServer(new InetSocketAddress(8080));
        server.start();
        System.out.println("WebSocket 服务器已启动，监听端口 8080");
    }
}
