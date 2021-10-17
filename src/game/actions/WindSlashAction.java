package game.actions;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.enemies.Enemies;
import game.enums.Abilities;
import game.enums.Status;
import game.weapons.StormRuler;

/**
 * class to perform Wind Slash Action
 *
 * @see game.actions.AttackAction
 */
public class WindSlashAction extends AttackAction{

    /**
     * the verb when performing Wind Slash
     */
    private String verb = "stuns";

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction
     */
    public WindSlashAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();
        int damage = ((StormRuler)weapon).WindSlashDamage();

        String result = actor + " " + verb + " and " + actor.getWeapon().verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            if (target.hasCapability(Abilities.PLAYER)) {
                Action resetAction = new SoftResetAction(null);
                result += System.lineSeparator() + resetAction.execute(actor, map);
            }
            else {
                ((Enemies) target).resetInstance(map, Status.ENEMIES_KILLED, null);
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

}
