package io.vertx.guides.wiki;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {

  
  @Override
  public void start() {
    
    vertx.createHttpServer()
        .requestHandler(req -> req.response().end("Hello Vert.x!"))
        .listen(8080);
  }
  
  
  @Override
  public void start(Future<Void> startFuture) throws Exception {
    
    startFuture.complete();
  }

}
