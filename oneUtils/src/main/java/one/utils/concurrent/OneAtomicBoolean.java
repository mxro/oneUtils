package one.utils.concurrent;

public interface OneAtomicBoolean {

	public boolean compareAndSet(boolean expect, boolean update);

	public boolean get();

	public boolean getAndSet(boolean newValue);

	public void set(boolean newValue);

}
