/**
 * 
 */
package one.utils.internal.keybuilder;

import java.util.List;

import one.utils.keybuilder.KeyBuilder;

/**
 * Allows to build an index based on the hash code of strings. It is recommended
 * to use this builder rather than {@link StringCharKeyBuilder} for long
 * strings.<br/>
 * This builder artifically shortens the generated api key to reduce the length
 * of key lookup paths. This builder should only be used if no or few collisions
 * can be expected within the key value range.
 * 
 * @see StringCharKeyBuilder
 * @author <a href="http://www.mxro.de/">Max Erik Rohde</a>
 */
public class StringShortHashKeyBuilder implements KeyBuilder<String> {

	private final StringHashKeyBuilder hashKeyBuilder;
	private final int keyLength;

	@Override
	public List<String> makeKey(final String key) {

		final List<String> wholeHashKey = hashKeyBuilder.makeKey(key);
		final List<String> genList = wholeHashKey.subList(0,
				Math.min(keyLength - 1, wholeHashKey.size()));

		return genList;
	}

	public StringShortHashKeyBuilder(final int keyLength) {
		this.hashKeyBuilder = new StringHashKeyBuilder();
		this.keyLength = keyLength;
	}

}
