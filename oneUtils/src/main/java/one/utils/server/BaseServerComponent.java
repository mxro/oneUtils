package one.utils.server;

public class BaseServerComponent implements ServerComponent {

	@Override
	public void stop(final ShutdownCallback callback) {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public void start(final StartCallback callback) {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public void injectConfiguration(final ComponentConfiguration conf) {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public void injectContext(final ComponentContext context) {
		throw new RuntimeException("Method not supported");
	}

}
