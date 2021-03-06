package one.utils.internal.keybuilder;

import java.io.UnsupportedEncodingException;
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

		public final String encode(final String input) {
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
			// System.out.println(resultStr);
			return resultStr.toString();
		}

		private final char toHex(final int ch) {
			return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
		}

		private final boolean isUnsafe(final char ch) {
			if (ch > 128 || ch < 0)
				return true;
			return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
		}

		public final String decodeUri(final String uri, final String encoding) {
			// assert (encoding != null && encoding.length() == 0) :
			// "Illegal encoding: "
			// + encoding;

			final int uriLenght = uri.length();

			int i = 0;
			final StringBuilder sb = new StringBuilder();
			char scannedCharacter;
			boolean changed = false;
			while (i < uriLenght) {
				scannedCharacter = uri.charAt(i);
				// System.out.println(scannedCharacter);
				switch (scannedCharacter) {
				case '+':
					sb.append(' ');
					i++;
					changed = true;
					break;
				case '%':
					try {
						final int startIndex = i;
						final byte[] bytes = new byte[(uriLenght - startIndex) / 3];
						int pos = 0;
						int deltaIndex = 0;
						while ((((startIndex + deltaIndex) + 2) < uriLenght)
								&& (scannedCharacter == '%')) {
							// build the interger
							final int v = Integer.parseInt(uri.substring(
									(startIndex + deltaIndex) + 1,
									(startIndex + deltaIndex) + 3), 16);
							if (v < 0) {
								throw new IllegalArgumentException(
										"Url decoding error.");
							}
							bytes[pos++] = (byte) v;

							deltaIndex = deltaIndex + 3;
							if (i < uriLenght) {
								scannedCharacter = uri.charAt((startIndex
										+ deltaIndex - 1));
							}
						}
						i = startIndex + deltaIndex;

						if ((i < uriLenght) && (scannedCharacter == '%'))
							throw new IllegalArgumentException(
									"Url decoding error.");

						try {
							sb.append(new String(bytes, 0, pos, encoding));
						} catch (final UnsupportedEncodingException e) {
							throw new RuntimeException(e);
						}
					} catch (final NumberFormatException e) {
						throw new IllegalArgumentException(
								"Url decoding error. Illegal number format: "
										+ e.getMessage());
					}
					changed = true;
					break;
				default:
					sb.append(scannedCharacter);
					i++;
					break;
				}
			}

			return (changed ? sb.toString() : uri);
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

		return new URLParamEncoder().decodeUri(keyElements.get(0), "UTF-8");

	}

}
