import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;


public class VerticleOne extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        StaticHandler staticHandler = StaticHandler.create().setWebRoot("static");

        Router router = Router.router(vertx);
        router.route("/static/*").handler(staticHandler);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080, asyncResult -> {

                    if (asyncResult.failed()) {
                        System.err.println("Could not start the HTTP server: " + asyncResult.cause());
                        startFuture.fail(asyncResult.cause());
                    } else {
                        System.out.println("HTTP server now running on port " + asyncResult.result().actualPort());
                        startFuture.complete();
                    }

                });

    }

}
