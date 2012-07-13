package one.utils.fn;

public class OneUtilsFn {

	public static <Type> Maybe<Type> is(final Type value) {
		return new Maybe<Type>() {

			@Override
			public boolean isDefined() {
				return true;
			}

			@Override
			public Type getValue() {
				return value;
			}
		};
	}

	public static <Type> Maybe<Type> undefined() {
		return new Maybe<Type>() {
			@Override
			public boolean isDefined() {
				return false;
			}

			@Override
			public Type getValue() {
				throw new RuntimeException(
						"Cannot access value of <undefined>.");
			}
		};
	}

}
