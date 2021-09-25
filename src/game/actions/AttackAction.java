package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.Player;
import game.enemies.Enemies;
import game.enemies.YhormTheGiant;
import game.enums.Status;
import game.weapons.YhormsGreatMachete;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}
		if (actor.getClass() == new YhormTheGiant().getClass() && ((YhormTheGiant)actor).isEnraged()){
			System.out.println("TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST");
			((YhormTheGiant)actor).getWeapon().getActiveSkill(target, direction).execute(actor, map);
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// if the dead target can trigger soft reset, means the target is player
			if (target.hasCapability(Status.SOFT_RESET)) {
				// if player is killed by enemies, no need to modify new location of token
				Action resetAction = new SoftResetAction(null);
				result += System.lineSeparator() + resetAction.execute(actor, map);
			}
			else if (target.getClass() == new YhormTheGiant().getClass()){
				((YhormTheGiant) target).killed(map);
				result += System.lineSeparator() + target + " HAS FALLEN.";
				((Enemies) target).transferSouls((Player)actor);
			}
			else {
				((Enemies) target).resetInstance(map, Status.ENEMIES_KILLED, null);
				// checks if the dead target is revived
				if (map.contains(target)) {
					result += System.lineSeparator() + target + " is revived.";
				} else {
					((Enemies) target).transferSouls((Player)actor);
					result += System.lineSeparator() + target + " is killed.";
				}
			}
		}
		return result;
	}

	public String menuDescription(Actor actor) {
		// only returns menu description when player attacks enemies
		String result = actor + " attacks " + target;
		result += " (" + ((Enemies)target).getHitPoints() + "/" + ((Enemies)target).getMaxHitPoints() + ")";
		// if the enemy is unarmed, no need to display its weapon
		if (!target.hasCapability(Status.UNARMED)) {
			result += " holding " + target.getWeapon();
		}
		result += " at " + direction;
		return result;
	}
}
