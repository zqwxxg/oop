package game.actions;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.enemies.Enemies;
import game.enums.Status;
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

    public RangedAttackAction(Actor target, String direction){
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
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
                    if (actor.getClass() != new Player("t", 't', 50).getClass()) {
                        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                            Location there = map.locationOf(target);
                            Weapon weapon = actor.getWeapon();
                            int damage = weapon.damage();
                            NumberRange xs, ys;
                            xs = new NumberRange(Math.min(currentLocation.x(), there.x()), Math.abs(currentLocation.x() - there.x()) + 1);
                            ys = new NumberRange(Math.min(currentLocation.y(), there.y()), Math.abs(currentLocation.y() - there.y()) + 1);

                            for (int x : xs) {
                                for (int y : ys) {
                                    if(map.at(x, y).getGround().blocksThrownObjects())
                                        return actor + " " + weapon.verb() + " attack is blocked.";
                                }
                            }
                            target.hurt(damage);
                            return actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
                        }
                    } else {
                        Location there = map.locationOf(target);
                        Weapon weapon = actor.getWeapon();
                        int damage = weapon.damage();

                        NumberRange xs, ys;
                        xs = new NumberRange(Math.min(currentLocation.x(), there.x()), Math.abs(currentLocation.x() - there.x()) + 1);
                        ys = new NumberRange(Math.min(currentLocation.y(), there.y()), Math.abs(currentLocation.y() - there.y()) + 1);

                        for (int x : xs) {
                            for (int y : ys) {
                                if(map.at(x, y).getGround().blocksThrownObjects())
                                    return actor + " " + weapon.verb() + " but the attack is blocked.";
                            }
                        }
                        target.hurt(damage);
                        return actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
                    }
                }
            }
        }
        return null;
    }



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
