package game.enemies;

import edu.monash.fit2099.engine.*;
import game.actions.UndeadDieAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.actions.AttackAction;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

/**
 * Class representing the enemy
 *
 * @see Actions
 * @see Actor
 * @see GameMap
 * @see Location
 * @see UndeadDieAction
 * @see AttackAction
 * @see Behaviour
 * @see AttackBehaviour
 * @see WanderBehaviour
 * @see FollowBehaviour
 * @see Resettable
 * @see Soul
 * @see Status
 */

public abstract class Enemies extends Actor implements Soul, Resettable {

    /**
     * The state that keeps track of the enemy's follow behaviour
     */
    protected boolean followBehaviourAdded = false;

    /**
     * The initial position of the enemy
     */
    protected Location initialPosition;

    /**
     * List of behaviours
     */
    protected ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * The count of souls of the enemy
     */
    protected int soulCount;

    /**
     * Constructor.
     *
     * @param name        the name of the enemy
     * @param displayChar the character that will represent the enemy in the display
     * @param hitPoints   the enemy's starting hit points
     * @param soulCount   the enemy's souls
     */
    public Enemies(String name, char displayChar, int hitPoints, int soulCount) {
        super(name, displayChar, hitPoints);
        this.soulCount = soulCount;
        registerInstance();
    }

    /**
     * Returns hit points of the enemy
     *
     * @return An integer that represents hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Returns max hit points of the enemy
     *
     * @return An integer that represents maxHitPoints
     */
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    /**
     * A setter for initialPosition attribute
     *
     * @param initialPosition the initial position of the enemy
     */
    public void setInitialPosition(Location initialPosition) {
        this.initialPosition = initialPosition;
    }

    /**
     * Returns a collection of the Actions that the Player can do to the current Enemy.
     *
     * Overrides Actor.getAllowableActions
     *
     * @see Actor#getAllowableActions(Actor, String, GameMap)
     * @param otherActor the Player that might be performing attack
     * @param direction  String representing the direction of the Player
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            // if follow behaviour is not added, add this into behaviours list
            // used to prevent adding redundant follow behaviour into behaviours list
            if (!followBehaviourAdded) {
                behaviours.add(new FollowBehaviour(otherActor));
                followBehaviourAdded = true;
            }
        }
        return actions;
    }

    /**
     * A useful method to clean up the list of instances in the ResetManager class
     *
     * Overrides Resettable.isExist()
     *
     * @see Resettable#isExist(GameMap)
     * @param map the map the enemy is on
     * @return the existence of the enemy in the game.
     * for example, true to keep it permanent, or false if enemy needs to be removed from the reset list.
     */
    @Override
    public boolean isExist(GameMap map) {
        return map.contains(this);
    }

    /**
     * Transfer current enemy's souls to another Soul instance.
     *
     * Overrides Soul.transferSouls()
     *
     * @see Soul#transferSouls(Soul)
     * @param soulObject a target soul object.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soulCount);
    }

    /**
     * Adds souls to current instance's souls.
     *
     * Overrides Soul.addSouls()
     *
     * @see Soul#addSouls(int)
     * @param souls number of souls to be incremented.
     * @return transaction status. True if addition successful, otherwise False.
     */
    @Override
    public boolean addSouls(int souls) {
        if (souls >= 0) {
            soulCount += souls;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add a behaviour to list of behaviours of an enemy.
     *
     * @param behaviour the behaviour that is being added to the list of behaviours
     */
    public void addBehaviour(Behaviour behaviour) {
        behaviours.add(behaviour);
    }
}
