## Vert.x Async Features



#### Run in Dev mode

Just use:
```bash
# mvn compile vertx:run
```

If you want to use a specific JDK to start during development, you can use either:

```bash
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home mvn compile vertx:run
```
or
```bash
# export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home
# mvn compile vertx:run
```