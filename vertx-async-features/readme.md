## Vert.x Async Features



### Run (in Dev mode)

Based on the lovely [Vert.x Maven Plugin]() 

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
made directly in the event loop, currently two different requests can be sent:

- using a blocking call:<br/>
  `time curl localhost:8080/?type=B`<br/>
  Example:
  ```bash
  $ time curl localhost:8080/?type=B
  { healthy: true, timestamp: 2018-05-09T09:12:22.927 }
  real	0m5.175s
  user	0m0.007s
  sys	0m0.010s
  $
  ```
- using an asynchronous call:<br/>
  `time curl localhost:8080/?type=ARH`<br/>
  Example:
  ```bash
  $ time curl localhost:8080/?type=ARH
  { healthy: true, timestamp: 2018-05-09T09:12:22.927 }
  real	0m5.175s
  user	0m0.007s
  sys	0m0.010s
  $
  ```
  