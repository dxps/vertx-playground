### Vert.x > `hello-microservice-http`

This service responds to (is a consumer of) messages sent to 'hello' address.

#### About

Project created using:
```bash
$ mkdir hello-message-service
$ cd hello-message-service

$ mvn io.fabric8:vertx-maven-plugin:1.0.8:setup   \
      -DprojectGroupId=io.vertx.hello             \
      -DprojectArtifactId=hello-message-service      \
      -Dverticle=io.vertx.hello.HelloMessageService  \
      -Ddependencies=infinispan
```

### Start

The application can be started in a so-called "development" mode using ``.
Included this command in `run-dev.sh` to quickly use it.

