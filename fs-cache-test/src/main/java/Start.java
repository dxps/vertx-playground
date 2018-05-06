import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Testing Vert.x issue #2423 (https://github.com/eclipse/vert.x/issues/2423).
 */
public class Start extends AbstractVerticle {

    @Override
    public void start() {

        System.setProperty("vertx.disableFileCaching", "true");

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new VerticleOne(), asyncResult -> {
            if (asyncResult.failed()) {
                System.err.println("VerticleOne experienced an issue: " + asyncResult.cause());
            } else {
                System.out.println("VerticleOne deployed");
            }

        });

    }

}
