package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author qianzhaoliang
 * @since 2019/08/02
 */
public class NioClient {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try ( SocketChannel socketChannel = SocketChannel.open()) {
            SocketAddress socketAddress = new InetSocketAddress("localhost", 9999);
            socketChannel.connect(socketAddress);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int sendCount = 0;
            while (sendCount < 10) {
                buffer.clear();
                buffer.put(("current time : " + System.currentTimeMillis()).getBytes());
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
                int readLenth = socketChannel.read(buffer);
                buffer.flip();
                byte[] bytes = new byte[readLenth];
                buffer.get(bytes);
                System.out.println(new String(bytes, "UTF-8"));
                buffer.clear();
                sendCount++;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
