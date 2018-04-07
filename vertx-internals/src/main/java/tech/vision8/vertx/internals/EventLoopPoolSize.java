package tech.vision8.vertx.internals;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;


/**
 * @author vision8
 */
public class EventLoopPoolSize {

    public static void main(String[] args) {

        System.out.println(" current thread: " + Thread.currentThread());
        System.out.println(" ---------------------------------------------");
        System.out.println(" index   thread name                 thread id");
        System.out.println(" ---------------------------------------------");

        // By default, the number of event loop threads are twice the number of CPU cores.
        // Example:
        //   * On my 8-core MBP, 16 event loop threads are created.
        //   * In this case, output shows "vert.x-eventloop-thread-0" to "vert.x-eventloop-thread-15".
        Vertx vertx = Vertx.vertx();

        // However, a custom value can be provided:
        // Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(10));

        for (int i = 0; i < 20; i++) {
            int index = i;
            vertx.setTimer(1, timerID -> {
                Thread currentThread = Thread.currentThread();
                System.out.printf("%6d   %s\t %d\n", index, currentThread.getName(), currentThread.getId());
            });
        }

        // used just to end the execution
        vertx.close();

    }

}
