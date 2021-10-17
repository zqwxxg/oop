package game.actions;

import edu.monash.fit2099.engine.*;
import game.items.Token;

/**
 * Class to retrieve token of souls
 *
 * @see edu.monash.fit2099.engine.Action
 * @see edu.monash.fit2099.engine.PickUpItemAction
 * @see Token
 */
public class RetrieveSoulAction extends PickUpItemAction {
    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public RetrieveSoulAction(Item item) {
        super(item);
    }

    /**
     * Allow player to retrieve the token of souls and add to inventory
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(item);
        actor.addItemToInventory(item);
        return actor + " retrieved souls (" + ((Token)item).getSoulCount() + " souls)";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * Overrides Action.menuDescription()
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor who is performing the action
     * @return a String
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " retrieve souls (" + ((Token)item).getSoulCount() + " souls)";
    }
}
