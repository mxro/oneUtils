/*******************************************************************************
 * Copyright 2011 Max Erik Rohde http://www.mxro.de
 * 
 * All rights reserved.
 ******************************************************************************/
package one.utils.server;

public interface ShutdownCallback {
	public void onShutdownComplete();

	public void onFailure(Throwable t);
}
