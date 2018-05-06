package io.vertx.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public class HelloMessageFaultyService extends AbstractVerticle {
	
	private String instanceId;
	
	@Override
	public void start() {
		
		this.instanceId = this.toString();
		
		// retrieve the event bus and register a consumer on the address 'hello'
		vertx.eventBus()
				.<String>consumer("hello", (Message<String> message) -> {
					// randomly behave as follows:
					// - respond normally
					// - respond with a failure
					// - no response is sent
					double chaos = Math.random();
					if (chaos < 0.6) {
						// respond normally
						String response = message.body().isEmpty() ? "hello" : "hello " + message.body();
						JsonObject jsonResponse = new JsonObject()
								.put("served-by", instanceId)
								.put("message", response);
						log("Replying normally.");
						message.reply(jsonResponse);
					} else if (chaos < 0.9) {
						// respond with a failure
						log("Replying with a failure.");
						message.fail(500, "message processing failure");
					} else {
						// do not respond, leading to a timeout on the consumer side
						log("Not replying.");
					}
					
				});
		
	}
	
	private void log(String message) {
		System.out.println(String.format("%s > %s", instanceId, message));
	}
	
}
