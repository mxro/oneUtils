package one.utils.concurrent;

/**
 * A simple lock implementation.
 * 
 * @author mroh004
 * 
 */
public interface OneLock {

	public abstract void lock();

	public abstract void unlock();

	public abstract boolean isHeldByCurrentThread();

}
