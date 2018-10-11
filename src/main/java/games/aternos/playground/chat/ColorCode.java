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

    private ChatColor origin;
    private Pattern pattern;
    private ColorCodeFilter filter = this::filter;
    private ColorCodeInverter inverter = (input, colorCode) -> {
        if (this.origin.getChar() != colorCode) {
            return input;
        }

        return ColorCode.this.invert(input);
    };

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

    /**
     * Takes an input string and searches for color codes matching
     * this very color code and replaces them with the inverted variants.
     *
     * @param input The input string to scan for color codes
     *
     * @return Returns the input string back with the inverted color codes
     */
    public String invert(String input) {
        Preconditions.checkNotNull(input, "'input' cannot be null");

        input = ChatColor.translateAlternateColorCodes('&', input);
        return input.replaceAll(this.pattern().pattern(), InvertedChatColor.of(this.origin).toString());
    }

    /**
     * Takes an input string and filters any color code matching
     * this very color code. Matching color codes are being removed from
     * the input string.
     *
     * @param input The input string to scan for color codes
     *
     * @return Returns back the filtered input string
     */
    public String filter(String input) {
        Preconditions.checkNotNull(input, "'input' cannot be null");

        return input.replaceAll(this.pattern().pattern(), "");
    }

    /**
     * Returns the {@link ColorCodeFilter} for this very {@link ColorCode} type.
     *
     * @return Returns the {@link ColorCodeFilter} for this very {@link ColorCode} type
     */
    public ColorCodeFilter getFilter() {
        return filter;
    }

    /**
     * Returns the {@link ColorCodeInverter} for this very {@link ColorCode} type.
     *
     * @return Returns the {@link ColorCodeInverter} for this very {@link ColorCode} type
     */
    public ColorCodeInverter getInverter() {
        return inverter;
    }

}
