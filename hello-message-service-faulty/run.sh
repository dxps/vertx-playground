#!/bin/sh

java -jar target/hello-message-service-faulty-1.0-SNAPSHOT.jar \
    --cluster -Djava.net.preferIPv4Stack=true

