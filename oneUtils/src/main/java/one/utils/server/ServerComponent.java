/*******************************************************************************
 * Copyright 2011 Max Erik Rohde http://www.mxro.de
 * 
 * All rights reserved.
 ******************************************************************************/
package one.utils.server;

/**
 * A component of a server process.
 * 
 * @author <a href="http://www.mxro.de/">Max Erik Rohde</a>
 * 
 *         Copyright Max Erik Rohde 2011. All rights reserved.
 */
public interface ServerComponent<Conf extends ComponentConfiguration<?>> {

	/**
	 * This service can be called to shutdown this components 'gracefully'.
	 * 
	 * @param callback
	 */
	public void stop(ShutdownCallback callback);

	/**
	 * Starting up this server component.
	 * 
	 * @param callback
	 */
	public void start(Conf conf, StartCallback callback);

}
