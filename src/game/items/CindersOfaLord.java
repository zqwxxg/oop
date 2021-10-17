package game.items;

import game.enemies.LordOfCinder;

/**
 * Class representing the cinder of a Lord
 */
public class CindersOfaLord extends PortableItem {
    /**
     * keep track of the owner of this Cinder of Lord
     */
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
