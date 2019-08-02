package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @author qianzhaoliang
 * @since 2019/08/02
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端开始工作：");
        ServerHandlerImpl handler = new ServerHandlerImpl();
        while (true) {
            selector.select();
            System.out.println("开始处理请求 ： ");
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                try {
                    if (key.isAcceptable()) {
                        handler.handleAccept(key);
                    }
                    if (key.isReadable()) {
                        System.out.println(handler.handleRead(key));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                keyIterator.remove();
            }
            System.out.println("完成请求处理。");
        }

    }
}
