import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("data/nio-demo.txt", "rw");
        FileChannel fc = accessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(48);
        int len = fc.read(buffer);

        int index = 1;
        while(len != -1) {
            buffer.flip();
            while(buffer.hasRemaining()) {
                System.out.println(index++ + ":" +  (char)buffer.get());
            }
            buffer.clear();
            len = fc.read(buffer);
         }
         accessFile.close();
    }
}
