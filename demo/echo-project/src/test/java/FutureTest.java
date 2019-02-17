import org.junit.Test;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class FutureTest {



    @Test
    public void testFuture() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future future =  executorService.submit(new Callable<Object>() {
            public Object call() throws Exception {
                System.out.println("获取数据中,等待5秒");
                Thread.sleep(5000L);
                return "xdclass future";
            }
        });

        System.out.println("主线程继续执行");

        System.out.println("获取到的数据"+future.get());


    }


}
