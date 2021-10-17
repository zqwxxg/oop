package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

/**
 * class to increase player maximum hit points
 *
 * @see edu.monash.fit2099.engine.Action
 */
public class IncreaseMaxHpAction extends Action {
    /**
     * the item's name
     */
    private String item = "Max HP modifier";
    /**
     * the item's price
     */
    private int price = 200;

    /**
     * Allow player to increase his maximum health by 25 hit points
     *
     * Overrides Action.execute()
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player)actor;
        menuDescription(actor);
        if(player.subtractSouls(price)){
            player.increaseMaxHitPoints(25);
            return player.toString() + " maximum hit points has been increased to " + player.getMaxHitPoints();
        }
        return "Not enough souls to buy " + item;
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
    public String menuDescription(Actor actor) {
        return actor.toString() + " buys " + item + " (+25HP) (200 souls)";
    }
}
