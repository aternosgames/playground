package games.aternos.playground.chat;

import org.bukkit.ChatColor;

/**
 * Represents a simple inverter for {@link ChatColor} and or Minecraft color codes.
 * Takes an input string and a character representing the color code that should be
 * inverted. The inverter scans through the string and replaces any color codes with
 * the inverted variants.
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
