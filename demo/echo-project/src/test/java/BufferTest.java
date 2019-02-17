import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class BufferTest {


    public void test(){

        //组合缓冲区
        CompositeByteBuf  compositeByteBuf = Unpooled.compositeBuffer();

        //创建一个堆缓冲区
        ByteBuf heapBuffer = Unpooled.buffer(16);

        //创建一个堆外缓冲区，直接内存
        ByteBuf directBuffer = Unpooled.directBuffer(16);

        compositeByteBuf.addComponents(heapBuffer,directBuffer);

    }

}
