package tech.vision8.vertx.async;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is a blocking verticle as the request processing is done in a blocking way.
 *
 * @author vision8
 */
public class StateCollectorVerticle extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(StateCollectorVerticle.class);

    private final StateCollector stateCollector = new StateCollector();

    @Override
    public void start() throws Exception {

        String address = config().getString("address", "isHealthy");

        vertx.eventBus()
                .<String>consumer(address,
                        message -> {
                            logger.debug("\"{}\" request processing begin", address);
                            State state = stateCollector.isHealthyUsingBlocking();
                            message.reply(state.toString());
                            logger.debug("\"{}\" request processing end", address);
                        }
                );

        logger.info("Started listening on address \"{}\"", address);

    }

}
