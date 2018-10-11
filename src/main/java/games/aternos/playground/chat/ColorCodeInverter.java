package games.aternos.playground.chat;

import com.google.common.base.Preconditions;
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
     * Performs the color code invert on the input string
     *
     * @param input     The input string to scan for color codes
     * @param colorCode The color code which should be inverted
     *
     * @return Returns the input string back with the inverted color codes
     */
    String invert(String input, char colorCode);

    /**
     * Performs the color code invert on the input string. This method
     * does not take any color code input as it performs the invert on all
     * color codes available.
     *
     * @param input The input string to scan for color codes
     *
     * @return Returns the input string back with the inverted color codes
     */
    static String invertAll(String input) {
        Preconditions.checkNotNull(input, "'input' cannot be null");

        for (ChatColor color : ChatColor.values()) {
            input = invertByChatColor(input, color);
        }

        return input;
    }

    /**
     * Performs the color code invert on the input string. This method uses
     * the {@link ComplementaryColorCodeInverter} class as inverter. Also,
     * this method uses the color code from the {@link ChatColor} object by
     * calling {@link ChatColor#getChar()}.
     * <p><br>
     * Calling this method is equal to {@code #invertByColorCode(input, ChatColor.GREEN.getChar());}
     *
     * @param input     The input string to scan for color codes
     * @param chatColor The {@link ChatColor} which should be inverted
     *
     * @return Returns the input string back with the inverted color codes
     *
     * @see ComplementaryColorCodeInverter
     */
    static String invertByChatColor(String input, ChatColor chatColor) {
        Preconditions.checkNotNull(input, "'input' cannot be null");
        Preconditions.checkNotNull(chatColor, "'chatColor' cannot be null");

        return ComplementaryColorCodeInverter.INSTANCE.invert(input, chatColor.getChar());
    }

    /**
     * Performs the color code invert on the input string. This method uses
     * the {@link ComplementaryColorCodeInverter} class as inverter.
     *
     * @param input     The input string to scan for color codes
     * @param colorCode The color code which should be inverted
     *
     * @return Returns the input string back with the inverted color codes
     *
     * @see ComplementaryColorCodeInverter
     */
    static String invertByColorCode(String input, char colorCode) {
        Preconditions.checkNotNull(input, "'input' cannot be null");

        return ComplementaryColorCodeInverter.INSTANCE.invert(input, colorCode);
    }

    /**
     * Returns the default {@link ColorCodeInverter} instance which is being used
     * for all invert operations the static utilities methods provide.
     *
     * @return Returns the default {@link ColorCodeInverter} instance
     *
     * @see ComplementaryColorCodeInverter#INSTANCE
     */
    static ColorCodeInverter getDefault() {
        return ComplementaryColorCodeInverter.INSTANCE;
    }

    /**
     * Creates the default {@link ColorCodeInverter} instance which is
     * {@link ComplementaryColorCodeInverter}.
     *
     * @return Returns the created {@link ComplementaryColorCodeInverter} instance
     *
     * @see ComplementaryColorCodeInverter
     */
    static ColorCodeInverter createDefault() {
        return new ComplementaryColorCodeInverter();
    }

}
