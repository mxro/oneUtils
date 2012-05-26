oneUtils
========

Develop shared codebases for JRE and GWT Apps. Core feature of this library: Abstract Concurrency API.

Please note:

- Check the blog post '[Threads in GWT?](http://maxrohde.com/2012/05/26/threads-in-gwt/)' for more infos about this library.
- Please subscribe to my [blog](http://maxrohde.com) for updates.
- This library has been developed as part of the [onedb](http://www.onedb.de) project.

Usage examples in the following for [Executors](https://github.com/mxro/oneUtils/blob/master/README.md#executors), 
[Timers](https://github.com/mxro/oneUtils/blob/master/README.md#timers) and [Collections](https://github.com/mxro/oneUtils/blob/master/README.md#collections)

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

## Timers

The oneUtils concurrency API provides an abstract API for the creation of Timers in GWT and JRE environments.

```java
    		final Concurrency con = OneUtilsJre.newJreConcurrency();
  		// ^-- replace with 'new GwtConcurrency()' for GWT environments
		// see https://gist.github.com/2791639

		// -----
		// Timer for one invocation
		// -----
		con.newTimer().scheduleOnce(200, new Runnable() {

			@Override
			public void run() {
				System.out.println("Do in 200 ms");
			}
		});

		// -----
		// Timer for multiple invocations
		// -----
		con.newTimer().scheduleRepeating(200, 100, new Runnable() {

			@Override
			public void run() {
				System.out.println("Do in 200 ms and then every 100 ms.");
			}
		});

		// -----
		// Timer with minimal delay
		// -----
		con.runLater(new Runnable() {

			@Override
			public void run() {
				System.out.println("Do in background thread.");
			}
		});
```

## Collections

The API further allows for an abstract way to create thread-safe collections (using `Collections.synchroizedList` etc. won't 
compile in a GWT app).

```java
		final Concurrency con = OneUtilsJre.newJreConcurrency();
		// ^-- replace with 'new GwtConcurrency()' for GWT environments
		// see https://gist.github.com/2791639

		// -----
		// Thread Safe List
		// -----

		// If any of the collections is instantiated in a GWT environment, they
		// are created as default (non-synchronized) collections, since in
		// GWT it is assured that there is no concurrent access to the
		// collections

		final List<String> threadSafeList = con.newCollection()
				.newThreadSafeList(String.class);

		threadSafeList.add("item1");

		// -----
		// Thread Safe Map
		// -----
		final Map<Integer, String> threadSafeMap = con.newCollection()
				.newThreadSafeMap(Integer.class, String.class);

		threadSafeMap.put(25, "text");

		// -----
		// Thread Safe Queue
		// -----
		final Queue<Integer> threadSafeQueue = con.newCollection()
				.newThreadSafeQueue(Integer.class);

		threadSafeQueue.add(25);

		// -----
		// Thread Safe Set
		// -----
		final Set<String> threadSafeSet = con.newCollection().newThreadSafeSet(
				String.class);
		threadSafeSet.add("element1");
  ```


