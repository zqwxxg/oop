package game;

import edu.monash.fit2099.engine.*;

public class WindSlash extends WeaponAction {
    private Charge charge;

    public WindSlash(StormRuler stormRuler, Charge charge){
        super(stormRuler);
        this.charge = charge;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
//        int damage = weapon.damage()*2;
//        String result = "";
//        if(charge.getIsFullyCharge()){
//            result += actor + " stuns " + target + " for " + damage + " damage.";
//        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
