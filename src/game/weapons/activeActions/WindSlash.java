package game.weapons.activeActions;

import edu.monash.fit2099.engine.*;
import game.weapons.StormRuler;

public class WindSlash extends WeaponAction {

    public WindSlash(StormRuler stormRuler){super(stormRuler);};

    @Override
    public String execute(Actor actor, GameMap map) {
        menuDescription(actor);
        int damage = weapon.damage()*2;
        int hitRate = weapon.chanceToHit();
        hitRate = 100;
        return "";
//
//        String result = "";
//        if(charge.getIsFullyCharge()){
//            result += actor + " stuns " + target + " for " + damage + " damage.";
//        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Perform Wind Slash";
    }
}
