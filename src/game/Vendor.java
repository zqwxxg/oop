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
 * Class representing the Vendor
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

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
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

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {return new DoNothingAction();}

}
