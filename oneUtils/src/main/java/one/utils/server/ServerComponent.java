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
public interface ServerComponent {

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
	public void start(StartCallback callback);

	/**
	 * Set a configuration for this component
	 * 
	 * @param conf
	 */
	public void injectConfiguration(ComponentConfiguration conf);

	/**
	 * Set a server context for this component
	 * 
	 * @param context
	 */
	public void injectContext(ComponentContext context);

	/**
	 * Retrive the current configuration for this component
	 * 
	 * @return
	 */
	public ComponentConfiguration getConfiguration();
}
