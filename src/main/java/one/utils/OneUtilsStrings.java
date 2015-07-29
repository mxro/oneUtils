package one.utils;

/**
 * Various utility methods for Strings and chars.
 * 
 * @author mroh004
 * 
 */
public class OneUtilsStrings {

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
