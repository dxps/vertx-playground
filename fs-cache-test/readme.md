## Vert.x File System Cache Test

This is a simple project that uses the lovely [vertx-maven-plugin](https://github.com/reactiverse/vertx-maven-plugin), very useful for Vert.x apps development.

### Run on development mode

Start using:
```bash
mvn compile vertx:run
```

or use an explicit JDK:
```bash
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home \
mvn compile vertx:run
```

### Static resource cache test

Accessing [http://localhost:8080/static](http://localhost:8080/static) should include the updates if either Java (in `src/main/java`) or resource (in `src/main/resources`) files are updated. The console will show that redeployment gets triggered by a Watcher that looks for such changes.
  