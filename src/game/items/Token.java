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
 * @see Soul
 */

public class Token extends PortableItem implements Soul {

    /**
     * The count of souls
     */
//    private static int soulCount;
     private int soulCount;

//    /**
//     * A token instance
//     */
//    private static Token tokenInstantiated;

    /***
     * Constructor.
     *
     * The token of souls is displayed as '$'
     */
    public Token(int soulCount) {
        super("Token of Souls", '$');
        this.soulCount = soulCount;
    }

    public int getSoulCount() {
        return soulCount;
    }

    //    /**
//     * Replaces the zero-parameter constructor and creates an instance of Token
//     *
//     * @return a new instance of Token if there is no Token instantiated, the instantiated Token otherwise
//     */
//    public static Token getInstance() {
//        soulCount = 0;
//        // since only one token is allowed to exist, if there is no token instantiated,
//        // return a new token instance, else return the instantiated token
//        // note that token instantiated is static
//        if (tokenInstantiated == null) {
//            tokenInstantiated = new Token();
//        }
//        return tokenInstantiated;
//    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soulCount);
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
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(portable)
            return new RetrieveSoulAction(this);

        return null;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        // cannot drop token
        return null;
    }
}
