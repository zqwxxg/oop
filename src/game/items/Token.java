package game.items;
import edu.monash.fit2099.engine.*;
import game.Player;
import game.actions.RetrieveSoulAction;
import game.interfaces.Soul;

/**
 * Class representing a token of souls
 *
 * @see edu.monash.fit2099.engine
 * @see Player
 * @see RetrieveSoulAction
 * @see Soul
 */

public class Token extends PortableItem implements Soul {

    /**
     * The count of souls
     */
    private int soulCount;

    /***
     * Constructor.
     *
     * The token of souls is displayed as '$'
     */
    public Token(int soulCount) {
        super("Token of Souls", '$');
        this.soulCount = soulCount;
    }

    /**
     * Returns the soul count of the Token.
     *
     * @return an integer representing soul count
     */
    public int getSoulCount() {
        return soulCount;
    }

    /**
     * Transfer current token's souls to another Soul instance.
     *
     * Overrides Soul.transferSouls()
     *
     * @see Soul#transferSouls(Soul)
     * @param soulObject a target soul object.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soulCount);
    }

    /**
     * Adds souls to current instance's souls.
     *
     * Overrides Soul.addSouls()
     *
     * @see Soul#addSouls(int)
     * @param souls number of souls to be incremented.
     * @return transaction status. True if addition successful, otherwise False.
     */
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

    /**
     * Deducts souls from current instance's souls.
     *
     * Overrides Soul.subtractSouls()
     *
     * @see Soul#subtractSouls(int)
     * @param souls number of souls to be decremented.
     * @return transaction status. True if subtraction successful, otherwise False.
     */
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

    /**
     * Removes a Token from the inventory of the Actor.
     * This method is called once per turn, if the Token is being carried.
     *
     * Overrides PortableItem.tick()
     *
     * @see PortableItem#tick(Location)
     * @param currentLocation The location of the actor carrying this Token.
     * @param actor The actor carrying this Token.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        transferSouls((Player) actor);
        // since token stores souls, so no need to save this item in inventory after retrieving as
        // it has converted into currency
        actor.removeItemFromInventory(this);
    }

    /**
     * Creates and returns an action to retrieve the Token's souls.
     *
     * Overrides PortableItem.getPickUpAction
     *
     * @see PortableItem#getPickUpAction(Actor)
     * @param actor an actor that will interact with this item
     * @return a new RetrieveSoulAction
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new RetrieveSoulAction(this);
    }

    /**
     * Returns null since we cannot drop Token.
     *
     * Overrides PortableItem.getDropAction()
     *
     * @see PortableItem#getDropAction(Actor)
     * @param actor an actor that will interact with this item
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        // cannot drop token
        return null;
    }
}
