package game.items;
import edu.monash.fit2099.engine.*;
import game.Player;
import game.interfaces.Soul;

public class Token extends PortableItem implements Soul {

    private static int soulCount;
    private static Token tokenInstantiated;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public Token(String name, char displayChar) {
        super(name, displayChar);
    }

    public static Token getInstance(String name, char displayChar) {
        soulCount = 0;
        if (tokenInstantiated == null) {
            tokenInstantiated = new Token(name, displayChar);
        }
        return tokenInstantiated;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soulCount);
        soulCount = 0;
    }

    @Override
    public boolean addSouls(int souls) {
        if (souls >= 0) {
            soulCount += souls;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean subtractSouls(int souls) {
        if (souls >= 0) {
            soulCount -= souls;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        transferSouls((Player) actor);
        // since token stores souls, so no need to save this item in inventory after retrieving as
        // it has converted into currency
        actor.removeItemFromInventory(this);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        // token cannot drop
        return null;
    }
}
