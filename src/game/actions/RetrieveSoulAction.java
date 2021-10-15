package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.items.Token;

public class RetrieveSoulAction extends PickUpItemAction {
    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public RetrieveSoulAction(Item item) {
        super(item);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(item);
        actor.addItemToInventory(item);
        return actor + " retrieved souls(" + ((Token)item).getSoulCount() + " souls)";
    }

    public String menuDescription(Actor actor){
        return actor + " retrieve souls(" + ((Token)item).getSoulCount() + " souls)";
    }
}
