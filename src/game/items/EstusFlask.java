package game.items;

import edu.monash.fit2099.engine.Item;
import game.enums.Capabilities;

public class EstusFlask extends Item {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    private int chargeCount;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public EstusFlask(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        chargeCount = 3;
        addCapability(Capabilities.ESTUSFLASK);
    }


    public int getChargeCount() {
        return chargeCount;
    }

    public void setChargeCount(int chargeCount) {
        if (chargeCount > -1) {
            this.chargeCount = chargeCount;
        }
    }

    public void resetChargeCount() {
        chargeCount = 3;
    }

}
