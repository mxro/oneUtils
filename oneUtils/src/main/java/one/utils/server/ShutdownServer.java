/*******************************************************************************
 * Copyright 2011 Max Erik Rohde http://www.mxro.de
 * 
 * All rights reserved.
 ******************************************************************************/
package one.utils.server;

/**
 * A server which can be shut down.
 * 
 * @author <a href="http://www.mxro.de/">Max Erik Rohde</a>
 * 
 *         Copyright Max Erik Rohde 2011. All rights reserved.
 */
public interface ShutdownServer {

	/**
	 * This service can be called to shutdown this server 'gracefully'.
	 * 
	 * @param callback
	 */
	public void executeShutdown(ShutdownCallback callback);

}
