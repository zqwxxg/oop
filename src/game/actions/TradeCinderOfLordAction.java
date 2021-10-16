package game.actions;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.enemies.AldrichTheDevourer;
import game.enemies.LordOfCinder;
import game.enemies.YhormTheGiant;
import game.items.CindersOfaLord;
import game.weapons.DarkmoonLongbow;
import game.weapons.YhormsGreatMachete;

import java.util.List;

/**
 * Class to trade Cinder of Lord with Vendor
 *
 * @see edu.monash.fit2099.engine.Action
 * @see CindersOfaLord
 * @see LordOfCinder
 */
public class TradeCinderOfLordAction extends Action {
    /**
     * keep track of which lord of cinder's cinder of lord we are trading
     */
    private LordOfCinder lordOfCinder;
    /**
     * the lord of cinder's weapon
     */
    private WeaponItem weapon;

    public TradeCinderOfLordAction(LordOfCinder lordOfCinder){
        this.lordOfCinder = lordOfCinder;
        if(lordOfCinder.getClass()==YhormTheGiant.class){
            this.weapon = new YhormsGreatMachete();
        } else if(lordOfCinder.getClass()== AldrichTheDevourer.class){
            this.weapon = new DarkmoonLongbow();
        }
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player)actor;
        String ret = "";
        List<Item> items = actor.getInventory();
        for(Item item : items){ //remove Cinder of Lord
            if(item.getClass() == CindersOfaLord.class){
                if(((CindersOfaLord) item).getLordOfCinder() == this.lordOfCinder){
                    actor.removeItemFromInventory(item);
                    break; }
            }
        }
        for(Item item : items){ //remove weapon
            if(item.asWeapon() != null){
                actor.removeItemFromInventory(item);
                break; }
        }
        actor.addItemToInventory(weapon); //add new weapon
        ret += actor + " traded Cinder of " + lordOfCinder.toString() + " for " + weapon.toString();
        return  ret;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " trades Cinder of " + lordOfCinder.toString();
    }
}
