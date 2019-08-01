import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qianzhaoliang
 * @since 2019/8/1
 */
public class ScatterGatherDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile sourceFile = new RandomAccessFile("data/nio-demo.txt", "r");
        FileChannel channel = sourceFile.getChannel();

        File file = new File("data/scatter-demo.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileChannel writeChannel = new RandomAccessFile(file, "rw").getChannel();

        ByteBuffer firstBuffer = ByteBuffer.allocate(10);
        ByteBuffer secondBuffer = ByteBuffer.allocate(10);
        ByteBuffer[] bufferArray = {firstBuffer, secondBuffer};
        channel.read(bufferArray);

        firstBuffer.flip();
        secondBuffer.flip();
        writeChannel.write(bufferArray);
        channel.close();
        writeChannel.close();

    }
 }
