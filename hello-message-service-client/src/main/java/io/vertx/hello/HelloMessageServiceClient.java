package io.vertx.hello;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.eventbus.Message;
import rx.Single;

import java.util.concurrent.TimeUnit;

public class HelloMessageServiceClient extends AbstractVerticle {
	
	@Override
	public void start() {
		
		vertx.createHttpServer()
				.requestHandler(req -> {
					EventBus bus = vertx.eventBus();
					Single<JsonObject> lukeReply = bus.<JsonObject>rxSend("hello", "Luke")
							// timeout treatment
							.subscribeOn(RxHelper.scheduler(vertx))
							.timeout(3, TimeUnit.SECONDS)
							.retry()
							// finally, getting the message body
							.map(Message::body);
					Single<JsonObject> leiaReply = bus.<JsonObject>rxSend("hello", "Leia")
							// timeout treatment
							.subscribeOn(RxHelper.scheduler(vertx))
							.timeout(3, TimeUnit.SECONDS)
							.retry()
							// finally, getting the message body
							.map(Message::body);
					
					Single.zip(lukeReply, leiaReply, (luke, leia) ->
							new JsonObject()
									.put("Luke", luke.getString("message")
											+ " from " + luke.getString("served-by"))
									.put("Leia", leia.getString("message")
											+ " from " + leia.getString("served-by"))
					)
							.subscribe(
									// print the final result
									result -> req.response().end(result.encodePrettily()),
									error -> req.response()
											.setStatusCode(500)
											.end(new JsonObject().put("error", error.getMessage()).encode()));
				})
				.listen(8082);
	}
	
}
