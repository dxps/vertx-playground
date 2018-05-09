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

        logger.debug("StateCollector > isHealthyUsingBlocking > begin");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ie) {
            // nothing to do
        }
        logger.debug("StateCollector > isHealthyUsingBlocking > end");
        return State.healthyNow();

    }

    /**
     * Using a Handler of an AsyncResult.
     * @param handler The function to be called when the processing is done.
     */
    public void isHealthyUsingAsyncResultHandler(Handler<AsyncResult<State>> handler) {

        logger.debug("StateCollector > isHealthyUsingAsyncResultHandler > begin");
        State state = isHealthyUsingBlocking();
        handler.handle(Future.succeededFuture(state));
        logger.debug("StateCollector > isHealthyUsingAsyncResultHandler > end");

    }

    /**
     * Using Future as a result, instead of taking as parameter a Handler of AsyncResult.
     * @return A future result, that is the state.
     */
    public Future<State> isHealthyUsingFuture() {

        logger.debug("StateCollector > isHealthyUsingFuture > begin");
        State state = isHealthyUsingBlocking();
        logger.debug("StateCollector > isHealthyUsingFuture > end");
        return Future.succeededFuture(state);

    }

}
