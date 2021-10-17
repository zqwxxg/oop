package game.actions;

import edu.monash.fit2099.engine.*;
import game.weapons.MeleeWeapon;
import game.Player;
import game.weapons.GiantAxe;

import java.util.List;

/**
 * class to buy Giant Axe
 *
 * @see edu.monash.fit2099.engine.Action
 */
public class BuyGiantAxeAction extends Action {
    /**
     * The type of weapon we are buying
     */
    private MeleeWeapon weapon = new GiantAxe();

    /**
     * Allow player to buy Giant Axe from Vendor
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
        String ret = "";
        menuDescription(actor);
        List<Item> items = actor.getInventory();
        if(player.subtractSouls(weapon.getPrice())){
            // loop through all inventory
            for(Item item : items){
                if(item.asWeapon() != null){
                    actor.removeItemFromInventory(item);
                    break; // after it removes that weapon, break the loop.
                }
            }
            actor.addItemToInventory(new GiantAxe());
            ret +=  weapon.toString() + " has been successfully bought by " + actor.toString();
        }else{
            ret += "Not enough souls to buy " + weapon.toString();
        }
        return ret;
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
        return actor.toString() + " buys " + weapon.toString() + " (" + weapon.getPrice() + " souls)";
    }

}
