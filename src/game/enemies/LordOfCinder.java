package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.items.CindersOfaLord;

/**
 * The boss of Design o' Souls
 */
public abstract class LordOfCinder extends Enemies {
    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints, int soulCount) {
        super(name, displayChar, hitPoints, soulCount);
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public void resetInstance(GameMap map, Status status, String direction) {
        if (status == Status.ENEMIES_KILLED) {
            map.locationOf(this).addItem(new CindersOfaLord(this));
            map.removeActor(this);
        } else {
            hitPoints = maxHitPoints;
            map.moveActor(this, initialPosition);
            // remove follow behaviour from behaviours list
            behaviours.removeIf(behaviour -> behaviour instanceof FollowBehaviour);
            followBehaviourAdded = false;
        }
    }
}

