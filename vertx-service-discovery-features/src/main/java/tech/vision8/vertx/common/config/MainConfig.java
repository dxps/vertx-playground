package tech.vision8.vertx.common.config;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simplistic main service configuration component.
 *
 * @author vision8
 */
public class MainConfig {

    private static MainConfig instance = new MainConfig();

    private static final Logger logger = LoggerFactory.getLogger(MainConfig.class);

    private JsonObject config;

    private boolean inited;

    /**
     * Get the instance of MainConfig.
     */
    public static MainConfig getInstance() {
        return instance;
    }


    /**
     * Initialize the configuration based on a config file.
     */
    public Future<Void> init(String configFilePath) {

        return init(Vertx.currentContext().owner(), configFilePath);

    }


    /**
     * Initialize the configuration based on a config file.<br/>
     * This version expects a provided vertx instance and it is used within the tests
     * as in that context Vertx.currentContext() does not exist.
     */
    public Future<Void> init(Vertx vertx, String configFilePath) {

        Future<Void> initConfigFuture = Future.future();

        ConfigStoreOptions configStoreOptions = new ConfigStoreOptions();
        if (configFilePath != null) {
            configStoreOptions.setType("file")
                .setConfig(new JsonObject().put("path", configFilePath));
        }

        ConfigRetriever retriever = ConfigRetriever.create(vertx,
            new ConfigRetrieverOptions().addStore(configStoreOptions));

        retriever.getConfig(ar -> {
            if (ar.failed()) {
                logger.error("Error loading config from '{}' file: {}", configFilePath, ar.cause().getMessage());
                initConfigFuture.fail(ar.cause());
            } else {
                this.config = ar.result();
                inited = true;
                logger.info("Loaded config from '{}' file.", configFilePath);
                initConfigFuture.complete();
            }
        });

        return initConfigFuture;

    }


    /** Get the config object. */
    public JsonObject getConfig() {
        return config;
    }


    /** It tells if this component is initialized. */
    public boolean isInited() {
        return inited;
    }


}
