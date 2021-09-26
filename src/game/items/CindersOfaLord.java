package game.items;

import edu.monash.fit2099.engine.*;
import game.grounds.Bonfire;

public class CindersOfaLord extends PortableItem {

    /***
     * Constructor.
     *
     * The cinders of a lord is displayed as 'O'
     */
    public CindersOfaLord() {
        super("Cinders of A Lord", 'O');
    }

    @Override
    public void tick(Location currentLocation) {
        if (currentLocation.getGround() instanceof Bonfire) {
            currentLocation.removeItem(this);
            System.out.println("NEW CHAPTER");
        }
    }
}

