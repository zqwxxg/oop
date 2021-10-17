package game.actions;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.enemies.Enemies;
import game.enemies.LordOfCinder;
import game.enums.Abilities;
import game.enums.Status;
import game.weapons.DarkmoonLongbow;
import game.weapons.RangedWeapon;

import java.util.Random;

public class RangedAttackAction extends Action {

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
     * Constructor
     *
     * @param target    The actor being attacked
     * @param direction Direction of the actor being attacked
     */
    public RangedAttackAction(Actor target, String direction){
        this.target = target;
        this.direction = direction;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();
        int damage = weapon.damage();
        String result = "";
        Random random = new Random();

        // DarkmoonLongbow has a crit chance passive
        if (weapon.getClass() == DarkmoonLongbow.class) {
            int chance = random.nextInt(100) + 1;
            if (chance <= 15) {
                damage *= 2;
            }
        }

        // This whole parts checks if there is anything in the way that can block the ranged attack, ie. a wall
        Location currentLocation = map.locationOf(actor);
        int startX = currentLocation.x() - ((RangedWeapon) actor.getWeapon()).getRange();
        int startY = currentLocation.y() - ((RangedWeapon) actor.getWeapon()).getRange();
        int endX = currentLocation.x() + ((RangedWeapon) actor.getWeapon()).getRange();
        int endY = currentLocation.y() + ((RangedWeapon) actor.getWeapon()).getRange();
        for (int counterY = startY; counterY <= endY; counterY++) {
            for (int counterX = startX; counterX <= endX; counterX++) {
                Location here = new Location(map, counterX, counterY);
                if (here.containsAnActor()) {
                    Actor otherActor = here.getActor();
                    if (actor.getClass() != Player.class) {
                        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                            Location there = map.locationOf(target);
                            damage = weapon.damage();
                            NumberRange xs, ys;
                            xs = new NumberRange(Math.min(currentLocation.x(), there.x()), Math.abs(currentLocation.x() - there.x()) + 1);
                            ys = new NumberRange(Math.min(currentLocation.y(), there.y()), Math.abs(currentLocation.y() - there.y()) + 1);

                            for (int x : xs) {
                                for (int y : ys) {
                                    if(map.at(x, y).getGround().blocksThrownObjects())
                                        return actor + " " + weapon.verb() + " attack is blocked.";
                                }
                            }
                            result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
                        }
                    } else {
                        Location there = map.locationOf(target);
                        damage = weapon.damage();

                        NumberRange xs, ys;
                        xs = new NumberRange(Math.min(currentLocation.x(), there.x()), Math.abs(currentLocation.x() - there.x()) + 1);
                        ys = new NumberRange(Math.min(currentLocation.y(), there.y()), Math.abs(currentLocation.y() - there.y()) + 1);

                        for (int x : xs) {
                            for (int y : ys) {
                                if(map.at(x, y).getGround().blocksThrownObjects())
                                    return actor + " " + weapon.verb() + " but the attack is blocked.";
                            }
                        }
                        result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
                    }
                }
            }
        }

        target.hurt(damage);

        if (!target.isConscious()) {
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // if the dead target can trigger soft reset, means the target is player
            if (target.hasCapability(Abilities.PLAYER)) {
                // if player is killed by enemies, no need to modify new location of token
                Action resetAction = new SoftResetAction(null);
                result += System.lineSeparator() + resetAction.execute(actor, map);
            } else {
                ((Enemies) target).resetInstance(map, Status.ENEMIES_KILLED, direction);
                // checks if the dead target is revived
                if (map.contains(target)) {
                    result += System.lineSeparator() + target + " is revived.";
                } else {
                    ((Enemies) target).transferSouls((Player) actor);
                    if (target instanceof LordOfCinder) {
                        result += System.lineSeparator() + target + " HAS FALLEN.";
                    } else {
                        result += System.lineSeparator() + target + " is killed.";
                    }
                }
            }
        }
        return result;
    }



    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @param actor The actor who is performing the action
     * @return The key we use for this Action in the menu, or null to have it assigned for you.
     */
    @Override
    public String menuDescription(Actor actor) {
        // only returns menu description when player attacks enemies
        String result = actor + " attacks " + target;
        result += " (" + ((Enemies)target).getHitPoints() + "/" + ((Enemies)target).getMaxHitPoints() + ")";
        // if the enemy is unarmed, no need to show its weapon
        if (!target.hasCapability(Status.UNARMED)) {
            result += " holding " + target.getWeapon();
        }
        return result;
    }
}
