package game.items;

import edu.monash.fit2099.engine.*;
import game.grounds.Bonfire;

public class CindersOfaLord extends PortableItem {

    /***
     * Constructor.
     */
    public CindersOfaLord(GameMap map) {
        super("Cinders of A Lord", 'O');
    }

    @Override
    public void tick(Location currentLocation) {
        if (currentLocation.getGround() instanceof Bonfire) {
            currentLocation.removeItem(this);
        }
    }
}

