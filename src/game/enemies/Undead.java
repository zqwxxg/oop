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
 */
public class Undead extends Enemies {

	private Random random = new Random();
	private final String[] ATTACK_PROMPT = {"thwacks", "punches"};


	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 */
	public Undead() {
		super("Undead", 'u', 50, 50);
		behaviours.add(new AttackBehaviour());
		behaviours.add(new WanderBehaviour());
		addCapability(Status.SPAWN_UNDEAD);
		addCapability(Status.UNARMED);
	}

	/**
	 * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
	 * You can do something else with this method.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */

	/**
	 * Figure out what to do next.
	 * FIXME: An Undead wanders around at random and it cannot attack anyone. Also, figure out how to spawn this creature.
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
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

	@Override
	public void resetInstance(GameMap map, Status status, String direction) {
		map.removeActor(this);
	}

	@Override
	public Weapon getWeapon() {
		for (Item item : inventory) {
			if (item.asWeapon() != null)
				return item.asWeapon();
		}
		return getIntrinsicWeapon();
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, ATTACK_PROMPT[random.nextInt(ATTACK_PROMPT.length)]);
	}
}

