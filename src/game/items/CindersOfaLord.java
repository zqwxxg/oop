package game.items;

import edu.monash.fit2099.engine.*;
import game.enemies.LordOfCinder;

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
}
