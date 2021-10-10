package game;

import edu.monash.fit2099.engine.*;
import game.actions.BuyBroadswordAction;
import game.actions.BuyGiantAxeAction;
import game.actions.IncreaseMaxHpAction;
import game.actions.TradeCinderOfLordAction;
import game.enemies.LordOfCinder;
import game.enums.Status;
import game.items.CindersOfaLord;

/**
 * Class that sells weapons
 *
 * @see edu.monash.fit2099.engine.Actor
 */
public class Vendor extends Actor {
    
    /**
     * Constructor for Vendor class
     * 
     * Vendor is named as Fire Keeper and represents by character 'F'
     */
    public Vendor(){
        super("Fire Keeper", 'F', 0);
        addCapability(Status.UNATTACKABLE);
        addCapability(Status.ENTER_FLOOR);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        actions.add(new BuyBroadswordAction());
        actions.add(new BuyGiantAxeAction());
        actions.add(new IncreaseMaxHpAction());
        for(Item item : otherActor.getInventory()){
            if(item.getClass()== CindersOfaLord.class){
                LordOfCinder lordOfCinder = ((CindersOfaLord)item).getLordOfCinder();
                actions.add(new TradeCinderOfLordAction(lordOfCinder));
            }
        }
        return actions;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {return new DoNothingAction();}

}
