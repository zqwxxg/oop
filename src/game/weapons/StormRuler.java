package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.weapons.activeActions.Charge;
import game.weapons.activeActions.WindSlash;

public class StormRuler extends Sword{
    private Charge charge = new Charge(this);
    private WindSlash windSlash = new WindSlash(this);

    public StormRuler(){
        super("Storm Ruler", '7', 70, "slash", 60, 2000);
        allowableActions.add(charge);
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if(charge.getIsFullyCharge()){
            allowableActions.remove(charge);
            allowableActions.add(windSlash);
            return windSlash;
        }else
            {return charge;}
    }

    public void changeToWindSlash(){
        this.damage *= 2;
        this.hitRate = 100;
    }

}
