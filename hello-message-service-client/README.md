

Project created using:
```bash
mkdir hello-message-service-client
cd hello-message-service-client

mvn io.fabric8:vertx-maven-plugin:1.0.8:setup              \
      -DprojectGroupId=io.vertx.hello                      \
      -DprojectArtifactId=hello-message-service-client     \
      -Dverticle=io.vertx.hello.HelloMessageServiceClient  \
      -Ddependencies=infinispan,rx
```