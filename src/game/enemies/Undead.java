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
 * @see Behaviour
 * @see AttackBehaviour
 * @see WanderBehaviour
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
	}

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

	@Override
	public void resetInstance(GameMap map, Status status, String direction) {
		map.removeActor(this);
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		// undead has 20 base attack hit points
		// randomly choose a verb from ATTACK_PROMPT
		return new IntrinsicWeapon(20, ATTACK_PROMPT[random.nextInt(ATTACK_PROMPT.length)]);
	}
}

