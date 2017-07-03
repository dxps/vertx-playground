#!/bin/sh

mvn compile vertx:run \
    -Dvertx.runArgs=" -cluster -Djava.net.preferIPv4Stack=true"
