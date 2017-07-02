### Vert.x > Hello Microservice exposed through HTTP

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

#### Run

Start the service in redeploy (development or restart on change) mode using:
```bash
# mvn compile vertx:run
```
