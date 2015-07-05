/**
 * 
 */
package one.utils.internal.keybuilder;

import java.util.Arrays;
import java.util.List;

import one.utils.keybuilder.ReversableKeyBuilder;


/**
 * Builds a key from elements of a String seperated by dots.<br/>
 * For Example:
 * 
 * <pre>
 * "element1.element2" --> ["element1", "element2"]
 * </pre>
 * 
 * @author <a href="http://www.mxro.de/">Max Erik Rohde</a>
 * 
 *         Copyright Max Erik Rohde 2011. All rights reserved.
 */
public class StringDotKeyBuilder implements ReversableKeyBuilder<String> {

	@Override
	public List<String> makeKey(final String key) {
		final String[] split = key.split("\\.");
		return Arrays.asList(split);
	}

	@Override
	public String retrieveKey(List<String> keyElements) {
		if (keyElements.size() == 0) {
			return "";
		}
		String key = keyElements.get(0);

		if (keyElements.size() > 1) {
			for (int i = 1; i < keyElements.size(); i++) {
				key = key + "." + keyElements.get(i);
			}
		}

		return key;
	}

}
