package tech.vision8.vertx.internals;

import io.vertx.core.Vertx;

/**
 * @author vision8
 */
public class ContextLook {
	
	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread());
		Vertx vertx = Vertx.vertx();
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
