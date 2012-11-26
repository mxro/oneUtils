package one.utils.server;

public interface StartCallback {

	public void onStarted();

	public void onFailure(Throwable t);

}
