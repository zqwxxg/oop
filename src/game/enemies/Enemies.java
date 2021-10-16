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

    @Override
    public boolean isExist(GameMap map) {
        return map.contains(this);
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soulCount);
    }

    @Override
    public boolean addSouls(int souls) {
        if (souls >= 0) {
            soulCount += souls;
            return true;
        } else {
            return false;
        }
    }

    public void addBehaviour(Behaviour behaviour) {
        behaviours.add(behaviour);
    }
}
