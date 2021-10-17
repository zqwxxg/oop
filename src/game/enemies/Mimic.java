package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.enums.Status;
import game.grounds.Chest;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.items.Token;
import game.weapons.Kicking;

import java.util.Random;

/**
 * Class representing a Mimic
 *
 * @see edu.monash.fit2099.engine.Actor
 * @see game.enemies.Enemies
 */
public class Mimic extends Enemies{
    /**
     * A random number generator
     */
    private Random random = new Random();

    /**
     * Constructor
     *
     * All Mimics are represented by an 'M' and have 100 hit points, 200 souls.
     */
    public Mimic(Location initialLocation) {
        super("Mimic", 'M', 100, 200);
        setInitialPosition(initialLocation);
        behaviours.add(new AttackBehaviour());
        addCapability(Status.UNARMED);
        addCapability(Status.NOT_WEAK_TO_STORM_RULER);
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
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for(Behaviour behaviour:behaviours){
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Allows Mimic to reset abilities, attributes, and items.
     *
     * @see Resettable#resetInstance(GameMap, Status, String)
     * @param map the map the Mimic is on
     * @param status the status of the action that triggers reset
     * @param direction the direction of the object that triggers reset
     */
    @Override
    public void resetInstance(GameMap map, Status status, String direction) {
        if (status == Status.ENEMIES_KILLED) {
            int prob = random.nextInt(3);
            if (prob == 0){
                map.locationOf(this).addItem(new Token(100));
            } else if (prob == 1){
                map.locationOf(this).addItem(new Token(100));
                map.locationOf(this).addItem(new Token(100));
            } else {
                map.locationOf(this).addItem(new Token(100));
                map.locationOf(this).addItem(new Token(100));
                map.locationOf(this).addItem(new Token(100));
            }
            map.removeActor(this);
        } else {
            hitPoints = maxHitPoints;
            map.removeActor(this);
            initialPosition.setGround(new Chest());
        }
    }

    /**
     * Allow Mimic to get his weapon
     *
     * @return Mimic's weapon
     */
    @Override
    public Weapon getWeapon() {
        return new Kicking();
    }
}
