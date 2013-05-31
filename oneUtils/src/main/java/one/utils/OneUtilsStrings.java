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
}
