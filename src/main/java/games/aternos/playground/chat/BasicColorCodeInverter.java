package games.aternos.playground.chat;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;

/**
 * A basic implementation of {@link ColorCodeInverter} using RegEx to
 * scan for and invert color codes.
 *
 * @version 1.0
 * @since 0.0.1
 */
public class BasicColorCodeInverter implements ColorCodeInverter {

    /**
     * An instance of {@code BasicColorCodeInverter} used by {@link ColorCodeInverter}
     * for 'default distribution'.
     *
     * @see ColorCodeInverter#invertAll(String)
     * @see ColorCodeInverter#invertByChatColor(String, ChatColor)
     * @see ColorCodeInverter#invertByColorCode(String, char)
     * @see ColorCodeInverter#getDefault()
     */
    static final BasicColorCodeInverter INSTANCE = new BasicColorCodeInverter();

    /**
     * {@inheritDoc} This method follows the simple principle of RegEx. When inverting
     * color codes this method uses {@linkplain String#replaceAll(String, String)} to replace
     * all color codes, matching the passed param {@code colorCode}, with the inverted color code.
     * <p><br>
     * In addition to that, when inverting, all '&' alternate color codes are being
     * translated/converted using {@link ChatColor#translateAlternateColorCodes(char, String)}.
     */
    @Override
    public String invert(String input, char colorCode) {
        Preconditions.checkNotNull(input, "'input' cannot be null");

        input = ChatColor.translateAlternateColorCodes('&', input);
        input = input.replaceAll("(i?)§" + String.valueOf(colorCode), // Color code to replace
                                 InvertedChatColor.of(colorCode).toString()); // Inverted color code

        return input;
    }

}
