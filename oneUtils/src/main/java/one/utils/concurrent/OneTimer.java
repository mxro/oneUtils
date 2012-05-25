package one.utils.concurrent;

/**
 * A simple timer interface, allows the timer to be stopped.
 * 
 * @author mroh004
 * 
 */
public interface OneTimer {

	/**
	 * Stops this timer. Running executions are not affected.
	 */
	public void stop();

}
