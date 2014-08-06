package one.utils;

/**
 * Various utility methods for Strings and chars.
 * 
 * @author mroh004
 * 
 */
public class OneUtilsStrings {

    public static final char[] allowedCharacters = { 'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '_', '-', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '0' };

    /**
     * Returns true if the given character is a 'standard' character. (a-z,
     * A-Z).
     * 
     * @param character
     * @return
     */
    public static boolean isSimpleCharacter(final char character) {
        boolean found = false;
        for (final char element : allowedCharacters) {
            found = found || character == element
                    || character == Character.toUpperCase(element);
        }
        return found;
    }

    /**
     * Counts the occurrences of <code>character</code> in <code>inStr</code>.
     * Starts scanning the <code>inStr</code> from the <b>end</b>. The first
     * time <code>untilChar</code> is encountered, the counting will immediately
     * stop.
     * 
     * @param character
     * @param inStr
     * @param untilChar
     * @return
     */
    public static int characterOccurs(final char character, final String inStr,
            final char untilChar) {
        int counter = 0;
        for (int i = inStr.length() - 1; i >= 0; i--) {

            final char comparChar = inStr.charAt(i);
            if (comparChar == character) {
                counter++;
            } else if (comparChar == untilChar) {
                return counter;
            }
        }
        return counter;
    }
}
