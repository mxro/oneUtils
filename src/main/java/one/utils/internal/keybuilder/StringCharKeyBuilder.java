/**
 * 
 */
package one.utils.internal.keybuilder;

import delight.strings.SanitizeStrings;

import java.util.ArrayList;
import java.util.List;

import one.utils.keybuilder.ReversableKeyBuilder;

/**
 * Divides a string into individual characters to build key paths. <br/>
 * For example:
 * 
 * <pre>
 * "mystr" --> "m"/"y"/"s"/"t"/"r"
 * </pre>
 * 
 * Any characters not allowed in Uris (including /) are converted into numerical
 * representations with at least two characters.
 * 
 * <br/>
 * It is recommended to use {@link StringHashKeyBuilder} for very long strings.
 * 
 * @see StringHashKeyBuilder
 * @author <a href="http://www.mxro.de/">Max Erik Rohde</a>
 * 
 *         Copyright Max Erik Rohde 2011. All rights reserved.
 */
public class StringCharKeyBuilder implements ReversableKeyBuilder<String> {

	@Override
	public List<String> makeKey(final String key) {
		final String wholeKey = key;
		final List<String> keyElements = new ArrayList<String>(
				wholeKey.length());

		for (final char c : wholeKey.toCharArray()) {
			if (SanitizeStrings.isUrlPathCharacter(c)) {
				keyElements.add(String.valueOf(c));
			} else {
				final byte b = (byte) c;
				String character = String.valueOf(b);
				if (character.length() > 2) {
					character = "0" + character;
				}
				keyElements.add(character);
			}
		}
		return keyElements;
	}

	@Override
	public String retrieveKey(final List<String> keyElements) {
		String key = "";
		for (final String element : keyElements) {
			if (element.length() < 2) {
				key = key + element;
			} else {
				final Byte character = Byte.valueOf(element);
				key = key + (char) character.byteValue();
			}
		}
		return key;
	}

}
