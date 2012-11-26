package one.utils.server;

/**
 * A factory for a {@link ServerComponent}
 * 
 * @author Max
 * 
 */
public interface ServerComponentFactory<C extends ServerComponent, Conf extends ComponentConfiguration<C>> {

	public C init(Conf conf);

}
