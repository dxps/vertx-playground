## Vert.x Service Discovery Features

This project explores the service discovery features available in Vert.x based solutions.

### Running _(in dev mode)_

As this project uses [vertx-maven-plugin](https://github.com/reactiverse/vertx-maven-plugin) 
that provides a nice development experience by hot reloading (restarting) the runtime context 
on Java code or resource changes, the startup can be done using `mvn compile vertx:run`.

If you want to use a specific JDK to start during development, you can use either:

```bash
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home mvn compile vertx:run
```
or
```bash
# export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home
# mvn compile vertx:run
```

For convenience, `scripts/run-dev.sh` script can be used.

