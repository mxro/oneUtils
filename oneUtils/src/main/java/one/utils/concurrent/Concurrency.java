package one.utils.concurrent;


/**
 * Basic concurrency operations, most of which can be emulated in a one-thread
 * enviromnet (like JavaScript).
 * 
 * @author mroh004
 * 
 */
public interface Concurrency {

	public abstract TimerFactory newTimer();

	public abstract ExecutorFactory newExecutor();

	public abstract void runLater(Runnable runnable);

	public abstract OneLock newLock();

	public abstract CollectionFactory newCollection();

}
