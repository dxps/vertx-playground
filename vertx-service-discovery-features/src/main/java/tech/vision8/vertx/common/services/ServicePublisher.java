package tech.vision8.vertx.common.services;


import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This component publishes the service to multiple service discovery platforms.
 * TODO: to be cont'd
 *
 * @author vision8
 */
public class ServicePublisher {

    private Record record;

    private static Logger logger = LoggerFactory.getLogger(ServicePublisher.class);

    private void publishToEventBus(Vertx vertx, String ebAddress, String serviceName) {

        ServiceDiscovery discovery = ServiceDiscovery.create(vertx,
                new ServiceDiscoveryOptions()
                        .setAnnounceAddress(ebAddress)
                        .setName(serviceName));

        Record record = new Record()
                .setType("eventbus-service-proxy")
                .setLocation(new JsonObject().put("endpoint", ebAddress))
                .setName(serviceName);

        discovery.publish(record, arr -> {
            if (arr.succeeded()) {
                this.record = arr.result();
                logger.debug("Service publishing to event bus done. Result: {}", this.record.toJson());
            } else {
                logger.error("Service publishing to event bus failed. Cause: {}", arr.cause().getMessage());
            }
        });

        discovery.close(); // release the reference

    }

}
