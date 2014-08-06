/**
 * 
 */
package one.utils.internal.keybuilder;

import java.util.List;

import one.utils.keybuilder.KeyBuilder;

/**
 * Allows to build an index based on the hash code of strings. It is recommended
 * to use this builder rather than {@link StringCharKeyBuilder} for long
 * strings.
 * 
 * @see StringCharKeyBuilder
 * @author <a href="http://www.mxro.de/">Max Erik Rohde</a> Copyright Max Erik
 *         Rohde 2011. All rights reserved.
 */
public class StringHashKeyBuilder implements KeyBuilder<String> {

	private final StringCharKeyBuilder charKeyBuilder;

	@Override
	public List<String> makeKey(final String key) {
		return charKeyBuilder.makeKey(Integer.toHexString(Math.abs(key
				.hashCode())));
	}

	public StringHashKeyBuilder() {
		this.charKeyBuilder = new StringCharKeyBuilder();
	}

}
