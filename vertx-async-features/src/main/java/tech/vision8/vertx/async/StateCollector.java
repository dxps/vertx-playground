package tech.vision8.vertx.async;


import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * This is a hypothetical component for collecting the state of the application.
 *
 * @author vision8
 */
public class StateCollector {

    private static final Logger logger = LoggerFactory.getLogger(StateCollector.class);

    /**
     * This is a classic way of processing something and this style implies a blocking call.
     */
    public State isHealthyUsingBlocking() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ie) {
            // nothing to do
        }
        return State.healthyNow();

    }

    /**
     *
     * @param handler
     */
    public void isHealthyUsingAsyncResultHandler(Handler<AsyncResult<State>> handler) {

        State state = isHealthyUsingBlocking();
        handler.handle(Future.succeededFuture(state));
        logger.debug("isHealthyUsingAsyncResultHandler > Response sent. ");

    }

}
