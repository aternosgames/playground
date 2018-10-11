package games.aternos.playground.chat;

import org.bukkit.ChatColor;

/**
 * Represents a filter for {@link ChatColor} and or Minecraft color codes.
 * Takes an input string that should be filtered. The filter scans through the
 * string and removes any color codes.
 *
 * @version 1.0
 * @since 0.0.1
 */
@FunctionalInterface
public interface ColorCodeFilter {

    /**
     * Performs the filtering on the input string.
     *
     * @param input     The input string to filter the color codes out
     *
     * @return Returns back the filtered input string
     */
    String filter(String input);

}
