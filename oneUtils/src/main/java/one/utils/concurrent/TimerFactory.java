package one.utils.concurrent;

/**
 * Simple factory to create timers for repeating and non-repeating tasks.
 * 
 * @author mroh004
 * 
 */
public interface TimerFactory {

	public OneTimer scheduleOnce(int when, Runnable runnable);

	public OneTimer scheduleRepeating(int offsetInMs, int intervallInMs,
			Runnable runnable);

}
