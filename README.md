oneUtils
========

Develop shared codebases for JRE and GWT Apps. Core feature of this library: Abstract Concurrency API.

Please check the post '[Threads in GWT?](http://maxrohde.com/2012/05/26/threads-in-gwt/)' for more infos.

This library has been developed as part of the [onedb](http://www.onedb.de) project.

Please subscribe to my [blog](http://maxrohde.com) for updates.

Find the usage examples below:

## Executors

The oneUtils concurrency API allows to define [Java Executor-style](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ExecutorService.html) 
executors in GWT and/or JRE apps.

```java
final Concurrency con = OneUtilsJre.newJreConcurrency();
// ^-- replace with 'new GwtConcurrency()' for GWT environments
// see https://gist.github.com/2791639

// -----
// Immediate Executor
// -----

// Immediate executors will execute the instructions immediately in the
// calling thread.
final OneExecutor immEx = con.newExecutor().newImmideateExecutor();
immEx.execute(new Runnable() {

@Override
public void run() {
System.out.println("Did immideately.");
}

});

// all executors must be shutdown to free resources in JRE environments
immEx.shutdown(new WhenExecutorShutDown() {

@Override
public void thenDo() {

}

@Override
public void onFailure(final Throwable t) {

}
});

// -----
// Single Thread Executor
// -----

// Single thread executors require the specification of an 'owner'
// object.
// This object can help in debugging by pointing to the place where
// the executor was created.
final OneExecutor singEx = con.newExecutor().newSingleThreadExecutor(
new Object() {
});

singEx.execute(new Runnable() {

@Override
public void run() {
System.out.println("Executed in different thread.");
}
});

// -----
// Multi-Thread Executor
// -----

// Multi-Thread Executors are backed by a Single Thread Executor in a
// GWT environment. In an JRE environment, they are backed by a
// Thread Pool.
final OneExecutor multEx = con.newExecutor().newParallelExecutor(3,
new Object() {
});

multEx.execute(new Runnable() {

@Override
public void run() {
System.out
.println("Potentially executed in parallel with other executions.");
}
});

```

