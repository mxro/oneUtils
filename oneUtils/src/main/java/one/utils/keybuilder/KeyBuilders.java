package one.utils.keybuilder;

import one.utils.internal.keybuilder.IdentityKeyBuilder;
import one.utils.internal.keybuilder.IntegerKeyBuilder;
import one.utils.internal.keybuilder.StringCharKeyBuilder;
import one.utils.internal.keybuilder.StringDotKeyBuilder;
import one.utils.internal.keybuilder.StringHashKeyBuilder;
import one.utils.internal.keybuilder.StringShortHashKeyBuilder;

public class KeyBuilders {

	public static ReversableKeyBuilder<Integer> newIntegerKeyBuilder() {
		return new IntegerKeyBuilder();
	}

	public static ReversableKeyBuilder<String> newStringCharKeyBuilder() {
		return new StringCharKeyBuilder();
	}

	public static ReversableKeyBuilder<String> newStringDotKeyBuilder() {
		return new StringDotKeyBuilder();
	}

	public static KeyBuilder<String> newStringHashKeyBuilder() {
		return new StringHashKeyBuilder();
	}

	public static KeyBuilder<String> newStringShortHashKeyBuilder(
			final int keyLength) {
		return new StringShortHashKeyBuilder(keyLength);
	}

	public static KeyBuilder<String> newIdentityStringBuilder() {
		return new IdentityKeyBuilder();
	}

}
