### Vert.x > `hello-microservice-http`

This service exposed through HTTP.

#### About

Project created using:
```bash
# mkdir hello-microservice-http
# cd hello-microservice-http
# mvn io.fabric8:vertx-maven-plugin:1.0.8:setup   \
      -DprojectGroupId=io.vertx.hello             \
      -DprojectArtifactId=hello-http-service      \
      -Dverticle=io.vertx.hello.HelloHttpService  \
      -Ddependencies=web
```

### Start

The application can be started in a so-called "development" mode or as a standard Java app.

#### In _dev_ mode

You can start the application in redeploy mode using `mvn compile vertx:run`

This mode is useful in development as the application gets restarted on class change.

Included this in `run-dev.sh` to quickly use it.

#### In _run_ mode

The application can be packages as a fat jar using `mvn package`.

Then you can start it using `java -jar target/hello-http-service-1.0-SNAPSHOT.jar`

### Usage

You can use this client by actually triggering its call to the server
by running something like `curl http://localhost:8080` or access `http://localhost:8080` from the browser.

Here is an complete example, including `jq` to prettify the output:
```bash
$ curl -s http://localhost:8080 | jq
{
    "message": "hello"
}
```
