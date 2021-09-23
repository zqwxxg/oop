package game.actions;

import java.nio.file.StandardCopyOption;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.Player;
import game.enemies.Enemies;
import game.enums.Status;

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
			// remove actor
			//TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
			if (target.hasCapability(Status.SOFT_RESET)) {
				// if player is killed by enemies, no need to modify new location of token
				Action resetAction = new SoftResetAction(null);
				result += System.lineSeparator() + resetAction.execute(actor, map);
			}
			else {
				((Enemies) target).transferSouls((Player)actor);
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		String result = actor + " attacks " + target;
		if (target instanceof Player) {
			Player otherActor = (Player) target;
			result += " (" + otherActor.getHitPoints() + "/" + otherActor.getMaxHitPoints() + ")";
		}
		else if (target instanceof Enemies){
			Enemies otherActor = (Enemies) target;
			result += " (" + otherActor.getHitPoints() + "/" + otherActor.getMaxHitPoints() + ")";
		}
		result += " at " + direction;
		return result;
	}
}
