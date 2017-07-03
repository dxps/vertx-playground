package io.vertx.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class HelloMessageService extends AbstractVerticle {
	
	@Override
	public void start() {
		
		// retrieve the event bus and register a consumer on the address 'hello'
		vertx.eventBus()
				.<String>consumer("hello", message -> {
					String response = message.body().isEmpty() ? "hello" : "hello " + message.body();
					JsonObject json = new JsonObject()
							.put("served-by", this.toString())
							.put("message", response);
					message.reply(json);
				});
		
	}
	
}
