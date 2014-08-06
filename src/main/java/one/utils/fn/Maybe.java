package one.utils.fn;

public interface Maybe<Type> {

	public boolean isDefined();

	public Type getValue();

}
