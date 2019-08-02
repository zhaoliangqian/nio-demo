import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author qianzhaoliang
 * @since 2019/08/02
 */
public class BufferDemo {

    public static void main(String[] args)  throws IOException {
        new BufferDemo().test1();
        System.out.println("");
        new BufferDemo().test_put();
    }

    public void test_put() {
        ByteBuffer buffer = ByteBuffer.allocate(3);
        String content = "content";
        buffer.put(content.getBytes(), 0, buffer.capacity());
        buffer.flip();
        while(buffer.hasRemaining()) {
            System.out.print((char)buffer.get());
        }
    }

    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        String content = "content";
        //如果长度超出buffer大小，会溢出错误
        buffer.put(content.getBytes());
        buffer.flip();

        while(buffer.hasRemaining()) {
            System.out.print((char)buffer.get());
        }
    }
}
