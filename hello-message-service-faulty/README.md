### Vert.x > `hello-message-service-faulty`

This service responds to (is a consumer of) messages sent to 'hello' address.
This version randomly responds 

#### About

Project created using:
```bash
$ mkdir hello-message-service-faulty
$ cd hello-message-service-faulty

$ mvn io.fabric8:vertx-maven-plugin:1.0.8:setup           \
      -DprojectGroupId=io.vertx.hello                     \
      -DprojectArtifactId=hello-message-service-faulty    \
      -Dverticle=io.vertx.hello.HelloMessageFaultyService \
      -Ddependencies=infinispan
```

### Start

The application can be started in a so-called "development" mode using ``.
Included this command in `run-dev.sh` to quickly use it.

A standard `run.sh` script is also included. This requires having the application packaged prior to use it. This means running `mvn clean package` before using it.
