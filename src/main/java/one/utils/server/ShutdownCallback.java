/*******************************************************************************
 * Copyright 2011 Max Erik Rohde http://www.mxro.de
 * 
 * All rights reserved.
 ******************************************************************************/
package one.utils.server;

/**
 * Callback, which can be used, when a service provider is shut down.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 * 
 */
@Deprecated
public interface ShutdownCallback {

    /**
     * This method is called when the shutdown could be completed successfully.
     */
    public void onShutdownComplete();

    /**
     * This method is called when an error occurs in the shutdown process.
     * 
     * @param t
     */
    public void onFailure(Throwable t);
}
