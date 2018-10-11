package games.aternos.playground.chat;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;

import java.util.regex.Pattern;

/**
 * Wraps all entries of {@link ChatColor} and adds additional functionality
 * and or utilities method. This enum (or 'singleton') is supposed to make
 * the work with color codes way easier by providing filters, inverters and
 * all kinds of stuff.
 *
 * @version 0.0.1
 * @see ChatColor
 * @since 1.0
 */
public enum ColorCode {

    BLACK(ChatColor.BLACK),
    DARK_BLUE(ChatColor.DARK_BLUE),
    DARK_GREEN(ChatColor.DARK_GREEN),
    DARK_AQUA(ChatColor.DARK_AQUA),
    DARK_RED(ChatColor.DARK_RED),
    DARK_PURPLE(ChatColor.DARK_PURPLE),
    GOLD(ChatColor.GOLD),
    GRAY(ChatColor.GRAY),
    DARK_GRAY(ChatColor.DARK_GRAY),
    BLUE(ChatColor.BLUE),
    GREEN(ChatColor.GREEN),
    AQUA(ChatColor.AQUA),
    RED(ChatColor.RED),
    LIGHT_PURPLE(ChatColor.LIGHT_PURPLE),
    YELLOW(ChatColor.YELLOW),
    WHITE(ChatColor.WHITE);

    private static final String PATTERN = "(i?)§";

    /**
     * Searches and returns the equivalent {@link ColorCode} type for the
     * passed parameter of type {@link ChatColor}.
     *
     * @param chatColor The {@link ColorCode} type
     *
     * @return The equivalent {@link ColorCode} type of the passed {@link ChatColor}
     */
    public static ColorCode of(ChatColor chatColor) {
        Preconditions.checkNotNull(chatColor, "'chatColor' cannot be null");

        return of(chatColor.getChar());
    }

    /**
     * Searches and returns the equivalent {@link ColorCode} type for the
     * passed parameter color code character.
     *
     * @param colorCode The color code character
     *
     * @return The equivalent {@link ColorCode} type of the passed color code
     */
    public static ColorCode of(char colorCode) {
        for (ColorCode pattern : values()) {
            if (pattern.origin().getChar() == colorCode) {
                return pattern;
            }
        }

        throw new IllegalStateException("No such color code '" + colorCode + "'");
    }

    /**
     * Paints the whole input string with the passed {@link ColorCode} type.
     * When painting, all colors are being stripped from the input string and the
     * passed {@link ColorCode} is being inserted at the very beginning of the
     * input string.
     *
     * @param colorCode The {@link ColorCode} type to paint the input string with
     * @param input     The input string to paint
     *
     * @return Returns back the painted input string
     */
    public static String paint(ColorCode colorCode, String input) {
        Preconditions.checkNotNull(input, "'input' cannot be null");
        Preconditions.checkNotNull(colorCode, "'colorCode' cannot be null");

        return colorCode.origin().toString() + ChatColor.stripColor(input);
    }

    /**
     * Takes an input string replaces all matching {@link ColorCode} (parameter {@code target})
     * types with another {@link ColorCode} (parameter {@code with}) type.
     *
     * @param target The targeted {@link ColorCode} type to replace
     * @param with   The replacement {@link ColorCode} type
     * @param input  The input string to apply the replacements on
     *
     * @return Returns back the input string with replaced color codes
     */
    public static String replace(ColorCode target, ColorCode with, String input) {
        Preconditions.checkNotNull(target, "'target' cannot be null");
        Preconditions.checkNotNull(with, "'with' cannot be null");
        Preconditions.checkNotNull(input, "'input' cannot be null");

        return input.replaceAll(target.pattern.pattern(), with.origin().toString());
    }

    /**
     * Takes an input string and searches for color codes matching
     * the passed {@link ColorCode} type and replaces them with the
     * inverted variants.
     *
     * @param colorCode The color code to invert
     * @param input     The input string to scan for color codes
     *
     * @return Returns back the input string with the inverted color codes
     */
    public static String invert(ColorCode colorCode, String input) {
        Preconditions.checkNotNull(colorCode, "'colorCode' cannot be null");
        Preconditions.checkNotNull(input, "'input' cannot be null");

        input = ChatColor.translateAlternateColorCodes('&', input);
        return input.replaceAll(colorCode.pattern().pattern(), InvertedChatColor.of(colorCode.origin).toString());
    }

    /**
     * Takes an {@link ColorCode} type and input string and filters any
     * color code matching the passed {@link ColorCode} type. Matching
     * color codes are being removed from the input string.
     *
     * @param colorCode The color code to filter out
     * @param input     The input string to scan for color codes
     *
     * @return Returns back the filtered input string
     */
    public static String filter(ColorCode colorCode, String input) {
        Preconditions.checkNotNull(colorCode, "'colorCode' cannot be null");
        Preconditions.checkNotNull(input, "'input' cannot be null");

        return input.replaceAll(colorCode.pattern().pattern(), "");
    }

    private ChatColor origin;
    private Pattern pattern;

    ColorCode(ChatColor origin) {
        this.origin = origin;
        this.pattern = Pattern.compile(PATTERN + String.valueOf(this.origin.getChar()));
    }

    /**
     * Returns the origin color code type {@link ChatColor}
     *
     * @return Returns the origin color code type {@link ChatColor}
     */
    public ChatColor origin() {
        return this.origin;
    }

    /**
     * Returns the RegEx pattern of this very color code.
     *
     * @return Returns the RegEx pattern of this very color code
     */
    public Pattern pattern() {
        return this.pattern;
    }

}
