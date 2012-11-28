package one.utils.server;

public class BaseServerComponent implements ServerComponent {

	private static final String METHOD_NOT_SUPPORTED = "Method not supported";

	@Override
	public void stop(final ShutdownCallback callback) {
		throw new RuntimeException(METHOD_NOT_SUPPORTED);
	}

	@Override
	public void start(final StartCallback callback) {
		throw new RuntimeException(METHOD_NOT_SUPPORTED);
	}

	@Override
	public void injectConfiguration(final ComponentConfiguration conf) {
		throw new RuntimeException(METHOD_NOT_SUPPORTED);
	}

	@Override
	public void injectContext(final ComponentContext context) {
		throw new RuntimeException(METHOD_NOT_SUPPORTED);
	}

	@Override
	public ComponentConfiguration getConfiguration() {
		throw new RuntimeException(METHOD_NOT_SUPPORTED);
	}

}
