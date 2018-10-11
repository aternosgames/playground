package games.aternos.playground.chat;

/**
 * Represents a simple inverter for Minecraft color codes. Takes an input
 * string and a character representing the color code. The whole input string
 * is being scanned for that color code and is being inverted by replacing the
 * actual color code literal with the inverted color code literal.
 * <p>
 * Inverted color codes can be found here: {@link InvertedChatColor}.
 *
 * @version 1.0
 * @see InvertedChatColor
 * @since 0.0.1
 */
@FunctionalInterface
public interface ColorCodeInverter {

    /**
     * Performs the color code invert on the input string.
     *
     * @param input     The input string to scan for color codes
     * @param colorCode The color code which should be inverted
     *
     * @return Returns the input string back with the inverted color codes
     */
    String invert(String input, char colorCode);

}
