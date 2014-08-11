package one.utils.tests;

import one.utils.concurrent.Concurrency;
import one.utils.concurrent.Executor;
import one.utils.concurrent.Executor.WhenExecutorShutDown;
import one.utils.jre.OneUtilsJre;

public class ExamplesExecutors {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final Concurrency con = OneUtilsJre.newJreConcurrency();
		// ^-- replace with 'new GwtConcurrency()' for GWT environments
		// see https://gist.github.com/2791639

		// -----
		// Immediate Executor
		// -----

		// Immediate executors will execute the instructions immediately in the
		// calling thread.
		final Executor immEx = con.newExecutor().newImmideateExecutor();
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
		final Executor singEx = con.newExecutor().newSingleThreadExecutor(
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
		final Executor multEx = con.newExecutor().newParallelExecutor(3,
				new Object() {
				});

		multEx.execute(new Runnable() {

			@Override
			public void run() {
				System.out
						.println("Potentially executed in parallel with other executions.");
			}
		});

	}
}
