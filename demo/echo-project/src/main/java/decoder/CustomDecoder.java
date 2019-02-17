package decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class CustomDecoder extends ByteToMessageDecoder {

    //in是服务端收到客户端的数据
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        //加入此处是接收int数据，
        //而int是4个字节，需要检查下是否满足
        //检查缓冲区中是否有4个字节
        if(in.readableBytes()>=4){
            //添加到解码信息里面去
            out.add(in.readInt());
        }
    }

}
