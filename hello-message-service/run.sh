#!/bin/sh

java -jar target/hello-message-service-1.0-SNAPSHOT.jar \
    --cluster -Djava.net.preferIPv4Stack=true

