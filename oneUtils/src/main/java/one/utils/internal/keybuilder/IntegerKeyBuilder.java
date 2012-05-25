/**
 * 
 */
package one.utils.internal.keybuilder;

import java.util.ArrayList;
import java.util.List;

import one.utils.keybuilder.ReversableKeyBuilder;


/**
 * @author <a href="http://www.mxro.de/">Max Erik Rohde</a>
 * 
 *         Copyright Max Erik Rohde 2011. All rights reserved.
 */
public class IntegerKeyBuilder implements ReversableKeyBuilder<Integer> {

	@Override
	public List<String> makeKey(final Integer key) {
		final String wholeKey = key.toString();
		final List<String> keyElements = new ArrayList<String>(
				wholeKey.length());
		for (final char c : wholeKey.toCharArray()) {
			keyElements.add(String.valueOf(c));
		}
		return keyElements;
	}

	@Override
	public Integer retrieveKey(final List<String> keyElements) {
		String key = "";
		for (final String element : keyElements) {
			key = key + element;
		}
		return Integer.valueOf(key);
	}

}
