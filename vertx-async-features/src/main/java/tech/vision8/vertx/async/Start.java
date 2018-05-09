package tech.vision8.vertx.async;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vision8
 */
public class Start extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    private final StateCollector stateCollector = new StateCollector();

    @Override
    public void start() throws Exception {

        vertx.createHttpServer()
                .requestHandler(req -> {

                    String type = req.getParam("type");
                    type = type == null ? "B" : type;

                    StateCheckType checkType = StateCheckType.valueOf(type);
                    final State state = new State();

                    switch (checkType) {
                        case B:
                            logger.debug("Collecting the state (using a blocking call)", checkType);
                            state.copyFrom(stateCollector.isHealthyUsingBlocking());
                            req.response()
                                    .putHeader("content-type", "text/plain")
                                    .end(state.toString());
                            break;
                        case ARH:
                            logger.debug("Collecting the state (using an AsyncResult Handler)", checkType);

                            // The proof that async does not mean non-blocking!!!
                            // stateCollector.isHealthyUsingAsyncResultHandler(ar -> state.copyFrom(ar.result()));

                            vertx.executeBlocking(future -> {
                                        State thestate = stateCollector.isHealthyUsingBlocking();
                                        future.complete(thestate);
                                    },
                                    ar -> {
                                        state.copyFrom((State) ar.result());
                                        req.response()
                                                .putHeader("content-type", "text/plain")
                                                .end(state.toString());
                                    });

                            break;
                    }

                })
                .listen(8080);

        logger.info("Web Server started on port 8080");

    }

}
