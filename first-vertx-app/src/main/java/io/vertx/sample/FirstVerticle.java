package io.vertx.sample;

import io.vertx.core.AbstractVerticle;

/**
 * Vert.x verticle using standard callbacks.
 */
public class FirstVerticle extends AbstractVerticle {

    @Override
    public void start() {

        vertx
              .createHttpServer()
              .requestHandler(request -> {
                  request
                        .response()
                        .end(String.format("Hello from '%s'.\n",
                              Thread.currentThread().getName()));
              })
              .listen(8080);
        
    }

}
