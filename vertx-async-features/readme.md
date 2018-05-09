## Vert.x Async Features



### Run (in dev mode)

Running "in dev mode" (as I prefer to consider since it hot reloads in case of code or resource changes) is based on the lovely [Vert.x Maven Plugin](https://github.com/reactiverse/vertx-maven-plugin). 

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

### Usage

In order to test the behavior of both blocking and non-blocking calls, synchronous or asynchronous,  
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

### Tests

Try sending at least two requests of the same time to see the difference of behavior (and thus response time) and Vert.x related warnings if event loop thread gets blocked.
Basically, you can just use ampersand to send the command to the background twice:
`time curl localhost:8080/?type=EB_2W &`

Complete example:
```bash
$ time curl localhost:8080/?type=EB_2W &
[1] 53832
$ time curl localhost:8080/?type=EB_2W &
[1] 53834
$ { healthy: true, timestamp: 2018-05-09T23:21:09.842 }
real	0m5.031s
user	0m0.007s
sys	0m0.008s
{ healthy: true, timestamp: 2018-05-09T23:21:10.239 }
real	0m5.025s
user	0m0.007s
sys	0m0.006s

[1]-  Done                    time curl localhost:8080/?type=EB_2W
[2]+  Done                    time curl localhost:8080/?type=EB_2W
$
```