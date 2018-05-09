## Vert.x Async Features



### Run (in dev mode)

Running "in dev mode" (as I prefer to consider since it hot reloads  
in case of code or resource changes) is based on the lovely 
[Vert.x Maven Plugin](https://github.com/reactiverse/vertx-maven-plugin). 

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

### Test

In order to test the behavior of both blocking and non-blocking calls 
made directly from the event loop, different approaches are implemented.

The following requests can be used:

- using a standard blocking call:<br/>
  `time curl localhost:8080/?type=B`<br/>
  
- using a non-blocking call:<br/>
  `time curl localhost:8080/?type=NB`<br/>
  
- using an asynchronous (but still blocking!) call using an `AsyncResult` `Handler`:<br/>
  `time curl localhost:8080/?type=ARH`<br/>
  
- using an asynchronous (but still blocking!) call using a `Future`:<br/>
  `time curl localhost:8080/?type=F`<br/>
  
- using an asynchronous and non-blocking approach using the event bus with 1 non worker verticle:<br/>
  `time curl localhost:8080/?type=EB_1NW`<br/>
    
- using an asynchronous and non-blocking approach using the event bus with 2 non worker verticles:<br/>
  `time curl localhost:8080/?type=EB_2NW`<br/>
  
- using an asynchronous and non-blocking approach using the event bus with 2 worker verticles:<br/>
  `time curl localhost:8080/?type=EB_2W`<br/>

