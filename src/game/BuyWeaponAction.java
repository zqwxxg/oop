package game;

import edu.monash.fit2099.engine.*;
import game.interfaces.Soul;

import java.util.List;

public class BuyWeaponAction extends Action {
    private WeaponItem item;
    private Soul soul; //need modification

    public BuyWeaponAction(WeaponItem weapon){
        this.item = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();
        List<Item> items = actor.getInventory();
        // loop through all inventory
        for(Item item : items){
            if(item.asWeapon() != null){
                actor.removeItemFromInventory(item);
                break; // after it removes that weapon, break the loop.
            }
        }
        actor.addItemToInventory(item);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        String result = item.toString() + " has been successfully bought.";
        return result;
    }
}
