package one.utils.jre.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import one.utils.concurrent.OneExecutor;

public class JavaExecutor implements OneExecutor {
	private final ExecutorService executor;

	@Override
	public void execute(final Runnable runnable) {
		executor.execute(runnable);
	}

	@Override
	public void shutdown(final WhenExecutorShutDown callback) {

		executor.shutdown();

		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					executor.awaitTermination(10000, TimeUnit.MILLISECONDS);
					callback.thenDo();
				} catch (final Throwable t) {
					callback.onFailure(t);
				}

			}

		};
		t.start();
		t = null;

	}

	public JavaExecutor(final ExecutorService executor) {
		super();
		this.executor = executor;
	}

}