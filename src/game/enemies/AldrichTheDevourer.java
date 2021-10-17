package game.enemies;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.behaviours.AttackBehaviour;
import game.interfaces.Behaviour;
import game.weapons.DarkmoonLongbow;

/**
 * Class representing Aldrich The Devourer
 *
 * @see edu.monash.fit2099.engine
 * @see Application
 * @see Behaviour
 * @see AttackBehaviour
 * @see DarkmoonLongbow
 */
public class AldrichTheDevourer extends LordOfCinder{

    /**
     * Constructor.
     */
    public AldrichTheDevourer() {
        super("Aldrich the Devourer", 'A', 350, 5000);
        inventory.add(new DarkmoonLongbow());
        behaviours.add(new AttackBehaviour());
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Aldrich.
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
        return super.getAllowableActions(otherActor, direction, map);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * Overrides LordOfCinder.playTurn()
     *
     * @see LordOfCinder#playTurn(Actions, Action, GameMap, Display)
     * @param actions    collection of possible Actions for this Aldrich
     * @param lastAction The Action this Aldrich took last turn.
     * @param map        the map containing the Aldrich
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!Application.enemiesList.contains(this)){
            Application.enemiesList.add(this);
        }
        for (Behaviour behaviour : behaviours) {
            Weapon weapon = this.getWeapon();
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                for (int counter = 0; counter < Application.enemiesList.size(); counter++){
                    if (Application.enemiesList.get(counter) == this){
                        Application.enemiesList.remove(counter);
                        Application.enemiesList.add(this);
                    }
                }
                return action;
            }
        }
        return new DoNothingAction();
    }
}
