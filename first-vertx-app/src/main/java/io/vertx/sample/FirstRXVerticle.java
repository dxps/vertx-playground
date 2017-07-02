package io.vertx.sample;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;

/**
 * Vert.x verticle using RxJava.
 */
public class FirstRXVerticle extends AbstractVerticle {
	
	@Override
	public void start() throws Exception {
		
		HttpServer server = vertx.createHttpServer();
		
		server
				.requestStream()
				.toObservable()
				.subscribe( request -> {
					request
							.response()
							.end(String.format("Hello from '%s'.\n",
									Thread.currentThread().getName()));
				});
		
		server
				.rxListen(8080)
				.subscribe();
		
	}
	
}
