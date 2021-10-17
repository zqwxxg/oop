package game.enemies;

import edu.monash.fit2099.engine.*;
import game.actions.UndeadDieAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.Random;

/**
 * An undead minion.
 *
 * @see edu.monash.fit2099.engine
 * @see UndeadDieAction
 * @see AttackBehaviour
 * @see WanderBehaviour
 * @see Behaviour
 * @see Status
 * @see Random
 */
public class Undead extends Enemies {

	/**
	 * A random number generator
	 */
	private Random random = new Random();

	/**
	 * List of verbs that describes the attack of undead
	 */
	private static final String[] ATTACK_PROMPT = {"thwacks", "punches"};

	/** 
	 * Constructor.
	 *
	 * All Undeads are represented by an 'u' and have 50 hit points, 50 souls.
	 */
	public Undead() {
		super("Undead", 'u', 50, 50);
		behaviours.add(new AttackBehaviour());
		behaviours.add(new WanderBehaviour());
		addCapability(Status.SPAWN_UNDEAD);
		addCapability(Status.UNARMED);
		addCapability(Status.NOT_WEAK_TO_STORM_RULER);
	}

	/**
	 * Select and return an action from list of behaviours to perform on the current turn.
	 *
	 * Overrides Enemies.playTurn()
	 *
	 * @see Enemies#playTurn(Actions, Action, GameMap, Display)
	 * @param actions    collection of possible Actions for this Undead
	 * @param lastAction The Action this Undead took last turn.
	 * @param map        the map containing the Undead
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// loop through all behaviours
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				// if undead is following player, skip wander behaviour
				if (followBehaviourAdded && behaviour instanceof WanderBehaviour) {
					continue;
				}
				// if undead wanders around, which means it is not under attack and not following player,
				// undead has a 10% chance to die instantly
				if (!followBehaviourAdded && behaviour instanceof WanderBehaviour) {
					if (random.nextInt(10)==0) {
						return new UndeadDieAction();
					}
				}
				return action;
			}
		}
		return new DoNothingAction();
	}

	/**
	 * Allows Undead to reset abilities, attributes, and items.
	 *
	 * Overrides Resettable.resetInstance()
	 *
	 * @see game.interfaces.Resettable#resetInstance(GameMap, Status, String)
	 * @param map the map the Undead is on
	 * @param status the status of the action that triggers reset
	 * @param direction the direction of the object that triggers reset
	 */
	@Override
	public void resetInstance(GameMap map, Status status, String direction) {
		map.removeActor(this);
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * Overrides Enemies.getIntrinsicWeapon()
	 *
	 * @see Enemies#getIntrinsicWeapon()
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		// undead has 20 base attack hit points
		// randomly choose a verb from ATTACK_PROMPT
		return new IntrinsicWeapon(20, ATTACK_PROMPT[random.nextInt(ATTACK_PROMPT.length)]);
	}
}

