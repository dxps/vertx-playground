## Vert.x > `hello-microservice-http-client`

This is the client of `hello-microservice-http` service, both of them being part of this playground.

### About

Created using:
```bash
$ mkdir hello-microservice-http-client
$ cd hello-microservice-http-client
$ mvn io.fabric8:vertx-maven-plugin:1.0.8:setup         \
      -DprojectGroupId=io.vertx.hello                   \
      -DprojectArtifactId=hello-http-service-client     \
      -Dverticle=io.vertx.hello.HelloHttpServiceClient  \
      -Ddependencies=web,web-client,rx
```

### Start

The application can be started in a so-called "development" mode or as a standard Java app.

#### In _dev_ mode

You can start the application in redeploy mode using `mvn compile vertx:run`

This mode is useful in development as the application gets restarted on class change.

Included this in `run-dev.sh` to quickly use it.

#### In _run_ mode

The application can be packages as a fat jar using `mvn package`.

Then you can start it using `java -jar target/hello-http-service-client-1.0-SNAPSHOT.jar`

### Usage

You can use this client by actually triggering its call to the server by running something like `curl http://localhost:8081` or go to `http://localhost:8081` from the browser.

Here is an complete example, including `jq` to prettify the output:
```bash
$ curl http://localhost:8081 | jq
{
    "Luke": "hello Luke",
    "Leia": "hello Luke"
}
```

