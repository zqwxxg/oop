package game.enemies;


import edu.monash.fit2099.engine.*;
import game.actions.SoftResetAction;
import game.actions.UndeadDieAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import java.util.Random;

/**
 * An undead minion.
 */
public class Undead extends Enemies {

	private final Random random = new Random();
	private static final String[] ATTACK_PROMPT = {"thwacks", "punches"};


	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50, 50);
		behaviours.add(new AttackBehaviour());
		behaviours.add(new WanderBehaviour());
		addCapability(Status.SPAWN_UNDEAD);
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

//	@Override
//	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//		Actions actions = super.getAllowableActions(otherActor, direction, map);
//		// only add follow behaviour once
//
//		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//		return actions;
//	}

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
		switch (status) {
			case REST -> {
				behaviours.removeIf(behaviour -> behaviour instanceof FollowBehaviour);
				followBehaviourAdded = false;
			}
			case SOFT_RESET -> map.removeActor(this);
		}
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

