package tech.vision8.vertx.async;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
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
                            logger.debug("Collecting the state (synchronously, using blocking call)");
                            state.copyFrom(stateCollector.isHealthyUsingBlocking());
                            req.response()
                                    .putHeader("content-type", "text/plain")
                                    .end(state.toString());
                            break;

                        case NB:
                            logger.debug("Collecting the state (asynchronously, using executeBlocking method)");
                            // an elegant option for simple use cases
                            vertx.executeBlocking(
                                    // the blocking code handler
                                    future -> {
                                        State thestate = stateCollector.isHealthyUsingBlocking();
                                        future.complete(thestate);
                                    },
                                    // the result handler
                                    ar -> {
                                        state.copyFrom((State) ar.result());
                                        req.response()
                                                .putHeader("content-type", "text/plain")
                                                .end(state.toString());
                                    }
                            );
                            break;

                        case ARH:
                            logger.debug("Collecting the state (asynchronously but blocking, using an AsyncResult Handler)");
                            // Note: this call is asynchronous but blocking!
                            stateCollector.isHealthyUsingAsyncResultHandler(ar -> state.copyFrom(ar.result()));
                            break;

                        case F:
                            logger.debug("Collecting the state (using a Future)");
                            // Note: this call is asynchronous but blocking!
                            stateCollector.isHealthyUsingFuture()
                                    .setHandler(
                                            ar -> {
                                                state.copyFrom(ar.result());
                                                req.response()
                                                        .putHeader("content-type", "text/plain")
                                                        .end(state.toString());
                                            }
                                    );
                            break;

                        case EB_1NW:
                            logger.debug("Collecting the state (using the event bus)");
                            vertx.eventBus()
                                    .send(StateCheckType.EB_1NW.name(), null,
                                            reply -> {
                                                String stateAsString = (String) reply.result().body();
                                                req.response()
                                                        .putHeader("content-type", "text/plain")
                                                        .end(stateAsString);
                                            }
                                    );
                            break;

                        case EB_2NW:
                            logger.debug("Collecting the state (using the event bus)");
                            vertx.eventBus()
                                    .send(StateCheckType.EB_2NW.name(), null,
                                            reply -> {
                                                String stateAsString = (String) reply.result().body();
                                                req.response()
                                                        .putHeader("content-type", "text/plain")
                                                        .end(stateAsString);
                                            }
                                    );
                            break;

                        case EB_2W:
                            logger.debug("Collecting the state (using the event bus)");
                            vertx.eventBus()
                                    .send(StateCheckType.EB_2W.name(), null,
                                            reply -> {
                                                String stateAsString = (String) reply.result().body();
                                                req.response()
                                                        .putHeader("content-type", "text/plain")
                                                        .end(stateAsString);
                                            }
                                    );
                            break;

                    }

                })
                .listen(8080);

        logger.info("Web Server started on port 8080");

        deployingOtherVerticles();

    }

    /**
     * Deploying additional verticles in multiple options.
     */
    private void deployingOtherVerticles() {

        DeploymentOptions deploymentOpts;
        // option 1: one verticle of type non worker (standard)
        deploymentOpts = new DeploymentOptions()
                .setConfig(new JsonObject().put("address", "EB_1NW"));
        vertx.deployVerticle(StateCollectorVerticle.class, deploymentOpts);

        // option 2: two verticles of type non worker (standard)
        deploymentOpts = new DeploymentOptions().setInstances(2)
                .setConfig(new JsonObject().put("address", "EB_2NW"));
        vertx.deployVerticle(StateCollectorVerticle.class, deploymentOpts);

        // option 3: two verticles of type worker
        deploymentOpts = new DeploymentOptions().setInstances(2).setWorker(true)
                .setConfig(new JsonObject().put("address", "EB_2W"));
        vertx.deployVerticle(StateCollectorVerticle.class, deploymentOpts);

    }

}
