package one.utils.server;

import java.io.Serializable;

/**
 * The configuration for a particular component of a server (for instance RPC
 * server)
 * 
 * @author Max
 * 
 */
public interface ComponentConfiguration extends Serializable {

	public boolean isBackgroundService();

	/**
	 * A unique ID for this component within the server it is running in.
	 * 
	 * @return
	 */
	public String getId();

}
