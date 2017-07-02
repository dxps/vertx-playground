package io.vertx.hello;

//import io.vertx.core.AbstractVerticle;

import io.vertx.core.json.JsonObject;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import rx.Single;

//import io.vertx.ext.web.Router;
//import io.vertx.ext.web.RoutingContext;
//import io.vertx.ext.web.client.HttpRequest;
//import io.vertx.ext.web.client.WebClient;
//import io.vertx.ext.web.codec.BodyCodec;

/**
 *
 */
public class HelloHttpServiceClient extends AbstractVerticle {
	
	//	private WebClient client;
	private WebClient client;
	
	@Override
	public void start() {

//		client = WebClient.create(vertx);
		
		client = io.vertx.rxjava.ext.web.client.WebClient.create(vertx);
		Router router = Router.router(vertx);
		
		router.get("/")
				.handler(this::invokeExternalHelloService);
		
		vertx.createHttpServer()
				.requestHandler(router::accept)
				.listen(8081);
		
	}
	
	private void invokeExternalHelloService(RoutingContext rc) {
		
		// ------- 1st example -------

//		System.out.println("\n------- A Classic Async Request (without RxJava) -------");
//
//		HttpRequest<JsonObject> request = client
//				.get(8080, "localhost", "/world%20of%20vert.x!")
//				.as(BodyCodec.jsonObject());
//
//		 emitting the request
//		request.send(ar -> { // ar = async request
//			if (ar.failed()) {
//				rc.fail(ar.cause());
//			} else {
//				rc.response().end(ar.result().body().encode());
//			}
//		});
		
		// ------- 2nd example -------
		
		// RxJava methods from Vert.x are prefixed with 'rx' to be easily recognizable
		
		System.out.println("\n------- Merge of Two Async Requests (with RxJava) -------");
		
		io.vertx.rxjava.ext.web.client.HttpRequest<JsonObject> request1 = client
				.get(8080, "localhost", "/Luke")
				.as(io.vertx.rxjava.ext.web.codec.BodyCodec.jsonObject());
		io.vertx.rxjava.ext.web.client.HttpRequest<JsonObject> request2 = client
				.get(8080, "localhost", "/Leia")
				.as(BodyCodec.jsonObject());
		
		Single<JsonObject> s1 = request1.rxSend().map(HttpResponse::body);
		Single<JsonObject> s2 = request1.rxSend().map(HttpResponse::body);
		
		Single.zip(s1, s2,
				(luke, leia) -> {
					// merging both results by adding them to the final response
					return new JsonObject()
							.put("Luke", luke.getString("message"))
							.put("Leia", leia.getString("message"));
				})
				.subscribe(
						// called with the result of zip()
						result -> rc.response().end(result.encodePrettily()),
						// called if something fails
						error -> rc.response().setStatusCode(500).end(error.getMessage())
				);
		
	}
	
}
