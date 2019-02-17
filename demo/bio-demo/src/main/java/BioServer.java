

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class BioServer {

    private static final int PORT = 8080;

    public static void main(String[] args)throws IOException {

        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            System.out.println("the time server is start in port :"+PORT);

            Socket socket = null;

            while (true){
                socket  =  server.accept();
                new Thread(new TimeServerHandler(socket)).start();
                //线程池  总共1000个请求，线程池数量100，等待队列500（BlockingQueue）,还有400个请求
                //一般高并发访问的时候，比如大学抢课，会出现拒绝链接，或者链接超时
                //超过线程池中的线程数量，就会拒绝
                //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor()

            }

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            if(server != null){
                System.out.println("the time server close");
                server.close();
            }
        }


    }
}
