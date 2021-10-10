package game.items;

import edu.monash.fit2099.engine.*;
import game.enemies.Enemies;
import game.enemies.LordOfCinder;
import game.grounds.Bonfire;

public class CindersOfaLord extends PortableItem {
    private LordOfCinder lordOfCinder;

    /***
     * Constructor.
     *
     * The cinders of a lord is displayed as 'O'
     */
    public CindersOfaLord(LordOfCinder lordOfCinder) {
        super("Cinders of A Lord", 'O');
        this.lordOfCinder = lordOfCinder;
    }

    public LordOfCinder getLordOfCinder() {
        return lordOfCinder;
    }

    @Override
    public void tick(Location currentLocation) {
        if (currentLocation.getGround() instanceof Bonfire) {
            currentLocation.removeItem(this);
            System.out.println("NEW CHAPTER");
        }
    }
}

