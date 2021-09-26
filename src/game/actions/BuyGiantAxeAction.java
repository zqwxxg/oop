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
    private MeleeWeapon weapon = new GiantAxe();

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

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " buys " + weapon.toString() + " (" + weapon.getPrice() + " souls)";
    }

}
