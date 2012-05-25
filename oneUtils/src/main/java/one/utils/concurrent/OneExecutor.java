package one.utils.concurrent;

public interface OneExecutor {

	public void execute(Runnable runnable);

	public interface WhenExecutorShutDown {

		/**
		 * Called when no threads spawned by this executor run anymore.
		 */
		public void thenDo();

		public void onFailure(Throwable t);
	}

	public void shutdown(WhenExecutorShutDown callback);

}
