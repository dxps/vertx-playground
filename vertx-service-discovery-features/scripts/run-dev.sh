#!/bin/sh

## file: scripts/run-dev.sh
## desc: This script is used for starting the service in the development mode.
##       It must be called as ./scripts/run-dev.sh from the project root directory.
## meta: 2018-05-16 | vision8 | 1st version

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home mvn compile vertx:run

