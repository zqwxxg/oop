package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

import java.util.Random;

/**
 * Class representing a skeleton
 *
 * @see edu.monash.fit2099.engine
 * @see WanderBehaviour
 * @see AttackBehaviour
 * @see FollowBehaviour
 * @see WanderBehaviour
 * @see Broadsword
 * @see GiantAxe
 * @see Status
 */

public class Skeleton extends Enemies {

    /**
     * A random number generator
     */
    private Random random = new Random();

    /**
     * The state that keeps track if skeleton is resurrectable
     */
    private boolean canRevive = true;

    /**
     * Constructor
     *
     * All Skeletons are represented by an 'S' and have 100 hit points, 250 souls.
     */
    public Skeleton() {
        super("Skeleton", 'S', 100, 250);
        // skeleton can hold either broadsword or giant axe
        WeaponItem[] weaponList = {new Broadsword(), new GiantAxe()};
        inventory.add(weaponList[random.nextInt(weaponList.length)]);
        behaviours.add(new AttackBehaviour());
        behaviours.add(new WanderBehaviour());
        addCapability(Status.RESURRECTABLE);
        addCapability(Status.NOT_WEAK_TO_STORM_RULER);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                // if skeleton is following player, skip wander behaviour
                if (followBehaviourAdded && behaviour instanceof WanderBehaviour) {
                    continue;
                }
                return action;
            }
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance(GameMap map, Status status, String direction) {
        // if skeleton is killed by player, it has 50% success rate to resurrect itself
        if (status == Status.ENEMIES_KILLED) {
            if (random.nextInt(2) == 0 && canRevive) {
                hitPoints = maxHitPoints;
                // can only revive once
                canRevive = false;
            } else {
                map.removeActor(this);
            }
            // else skeleton is resetted if player rests or dies
        } else {
            hitPoints = maxHitPoints;
            map.moveActor(this, initialPosition);
            // remove follow behaviour from behaviours list
            behaviours.removeIf(behaviour -> behaviour instanceof FollowBehaviour);
            followBehaviourAdded = false;
        }
    }
}
