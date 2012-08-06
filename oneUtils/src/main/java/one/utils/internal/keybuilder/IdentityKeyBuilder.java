package one.utils.internal.keybuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import one.utils.keybuilder.ReversableKeyBuilder;

public class IdentityKeyBuilder implements ReversableKeyBuilder<String> {

	/**
	 * see http://stackoverflow.com/a/4605816/270662
	 * 
	 * @author mroh004
	 * 
	 */
	private class URLParamEncoder {

		public String encode(final String input) {
			final StringBuilder resultStr = new StringBuilder();
			for (final char ch : input.toCharArray()) {
				if (isUnsafe(ch)) {
					resultStr.append('%');
					resultStr.append(toHex(ch / 16));
					resultStr.append(toHex(ch % 16));
				} else {
					resultStr.append(ch);
				}
			}
			return resultStr.toString();
		}

		private char toHex(final int ch) {
			return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
		}

		private boolean isUnsafe(final char ch) {
			if (ch > 128 || ch < 0)
				return true;
			return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
		}

	}

	@Override
	public List<String> makeKey(final String key) {
		final List<String> keyList = new ArrayList<String>(1);
		keyList.add(new URLParamEncoder().encode(key));
		return keyList;
	}

	@Override
	public String retrieveKey(final List<String> keyElements) {
		try {
			return URLDecoder.decode(keyElements.get(0), "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
