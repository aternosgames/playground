package games.aternos.playground.chat;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;

/**
 * Defines all {@link ChatColor} entries with their inverted
 * {@link ChatColor} variant.
 *
 * @version 1.0
 * @since 0.0.1
 */
public enum InvertedChatColor {

    BLACK(ChatColor.WHITE),
    DARK_BLUE(ChatColor.YELLOW),
    DARK_GREEN(ChatColor.LIGHT_PURPLE),
    DARK_AQUA(ChatColor.RED),
    DARK_RED(ChatColor.AQUA),
    DARK_PURPLE(ChatColor.GREEN),
    GOLD(ChatColor.BLUE),
    GRAY(ChatColor.DARK_GRAY),
    DARK_GRAY(ChatColor.GRAY),
    BLUE(ChatColor.GOLD),
    GREEN(ChatColor.DARK_PURPLE),
    AQUA(ChatColor.DARK_RED),
    RED(ChatColor.DARK_AQUA),
    LIGHT_PURPLE(ChatColor.DARK_GREEN),
    YELLOW(ChatColor.DARK_BLUE),
    WHITE(ChatColor.BLACK);

    /**
     * Retrieves the inverted {@link ChatColor} variant of the passed
     * {@link ChatColor} type.
     *
     * @param chatColor The {@link ChatColor} type to invert
     *
     * @return The inverted {@link ChatColor} variant
     */
    public static ChatColor of(ChatColor chatColor) {
        Preconditions.checkNotNull(chatColor, "'chatColor' cannot be null");

        return of(chatColor.getChar());
    }

    /**
     * Retrieves the inverted {@link ChatColor} variant of the passed
     * color code.
     *
     * @param colorCode The color code to invert
     *
     * @return The inverted {@link ChatColor} variant of the color code
     */
    public static ChatColor of(char colorCode) {
        for (ChatColor color : ChatColor.values()) {
            if (color.getChar() == colorCode) {
                return valueOf(color.name()).getInverted();
            }
        }

        throw new IllegalStateException("No such color code '" + colorCode + "'");
    }

    private ChatColor inverted;

    InvertedChatColor(ChatColor inverted) {
        this.inverted = inverted;
    }

    /**
     * Returns the inverted {@link ChatColor} variant
     */
    public ChatColor getInverted() {
        return this.inverted;
    }

}
