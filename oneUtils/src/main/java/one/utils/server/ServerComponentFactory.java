package one.utils.server;

/**
 * A factory for a {@link ServerComponent}
 * 
 * @author Max
 * 
 */
public interface ServerComponentFactory<Comp extends ServerComponent, Conf extends ComponentConfiguration<Comp>> {

	public Comp init(Conf conf);

}
