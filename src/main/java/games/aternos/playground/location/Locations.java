package games.aternos.playground.location;

import org.bukkit.Location;

import java.util.Random;

/**
 * Contains usefull operations for locations.
 */
public class Locations {

    /**
     * Returns new location inside area of {@code radius} blocks around given location.
     *
     * @param loc           given location
     * @param radius        size of area for new location
     * @param excludeHeight if true the new location will only contain modified x and z values
     * @return new location
     */
    public static Location spread(Location loc, int radius, boolean excludeHeight) {
        Random rand = new Random();
        return loc.clone().add(
            rand.nextInt(radius*2) - radius,
               (excludeHeight ? 0 : rand.nextInt(radius*2) - radius),
            rand.nextInt(radius*2) - radius
        );
    }
}
