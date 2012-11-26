package one.utils.server;

import java.io.Serializable;

/**
 * The configuration for a particular component of a server (for instance RPC
 * server)
 * 
 * @author Max
 * 
 */
public interface ComponentConfiguration<C extends ServerComponent> extends
		Serializable {

	public C init();

	public boolean isBackgroundService();

}
