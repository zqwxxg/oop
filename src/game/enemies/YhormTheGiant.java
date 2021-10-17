package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.interfaces.Behaviour;
import game.weapons.StormRuler;
import game.weapons.YhormsGreatMachete;
import game.weapons.activeActions.WindSlash;

/**
 * Class representing Yhorm The Giant
 *
 * @see edu.monash.fit2099.engine
 * @see AttackBehaviour
 * @see Behaviour
 * @see YhormsGreatMachete
 * @see WindSlash
 */

public class YhormTheGiant extends LordOfCinder{
    /**
     * return true if Yhorm is stunned (when performing Wind Slash Action)
     */
    private boolean isStunned;

    /**
     * Constructor for YhormTheGiant class
     *
     * All Yhorm the Giant are represented by an 'Y' and have 500 hit points, 5000 souls.
     */
    public YhormTheGiant() {
        super("Yhorm the Giant", 'y', 500, 5000);
        inventory.add(new YhormsGreatMachete());
        behaviours.add(new AttackBehaviour());
    }

    /**
     * setter for isStunned attribute
     */
    public void setIsStunned(){
        isStunned = true;
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Yhorm.
     *
     * Overrides LordOfCinder.getAllowableActions()
     *
     * @see LordOfCinder#getAllowableActions(Actor, String, GameMap)
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        Weapon weapon = otherActor.getWeapon();
        if(weapon.getClass()== StormRuler.class){ //allow Player to perform Wind Slash Action
            if(((StormRuler)weapon).getCharge().getIsFullyCharge()){
                actions.add(new WindSlash((StormRuler)weapon));
                return actions;
            }
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * Overrides LordOfCinder.playTurn()
     *
     * @see LordOfCinder#playTurn(Actions, Action, GameMap, Display)
     * @param actions    collection of possible Actions for this Yhorm
     * @param lastAction The Action this Yhorm took last turn.
     * @param map        the map containing the Yhorm
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(isStunned){
            isStunned = false; //set isStunned to false so Yhorm is back to normal and can perform other actions
            return new DoNothingAction(); //do nothing when Yhorm is stunned
        }
        for (Behaviour behaviour : behaviours) {
            Weapon weapon = this.getWeapon();
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Checks if Yhotm is enraged.
     *
     * @return true if Yhorm is in ember form, false otherwise
     */
    public boolean isEnraged() {
        ((YhormsGreatMachete)getWeapon()).rageModeTest(this);
        return getHitPoints() < (getMaxHitPoints()/2);
    }
}
