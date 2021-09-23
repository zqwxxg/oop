package game;

import edu.monash.fit2099.engine.*;

public class StormRulerNormalAttack extends AttackAction {

    public StormRulerNormalAttack(Actor target, String direction){ super(target, direction);}

    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();
        int damage = weapon.damage();
        int hitRate = rand.nextInt(100);
        String result = "";
        if (!(hitRate <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        } else if (hitRate <= 20) {
            damage = weapon.chanceToHit()*2;
            result += "Critical Strike! ";
        }
        result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            // remove actor
            //TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
            map.removeActor(target);
            result += System.lineSeparator() + target + " is killed.";
        }
        return result;
    }

    public String menuDescription(Actor actor) {
        String ret = "Attack the enemy";
        return ret;
    }
}
