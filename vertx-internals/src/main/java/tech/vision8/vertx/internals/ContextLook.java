package tech.vision8.vertx.internals;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author vision8
 */
public class ContextLook {
	
	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread());
		
		// By default, the number of event loop threads are twice the number of CPU cores.
		// Example:
		//   * On my 8-core MBP, 16 event loop threads are created.
		//   * In this case, output shows "vert.x-eventloop-thread-0" to "vert.x-eventloop-thread-15".
		// Vertx vertx = Vertx.vertx();
		
		// However, a custom value can be provided:
		Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(10));
		
		for (int i = 0; i < 20; i++) {
			int index = i;
			vertx.setTimer(1, timerID -> {
				Thread currentThread = Thread.currentThread();
				System.out.println(
						String.format("%2d:\t thread name: %s  \t id: %d", index, currentThread.getName(), currentThread.getId()));
			});
		}
		
		// used just to end the execution
		vertx.close();
		
	}
	
}
