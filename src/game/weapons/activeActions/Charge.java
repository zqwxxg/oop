package game.weapons.activeActions;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import game.weapons.StormRuler;

public class Charge extends WeaponAction {
    private int currentCharge = 0;
    private boolean isFullyCharge = false;
    private static final int MAX_CHARGE = 3;

    public Charge(StormRuler stormRuler){
        super(stormRuler);
    }

    public boolean getIsFullyCharge(){
        return isFullyCharge;
    }

    public String execute(Actor actor, GameMap map) {
        menuDescription(actor);
        String ret = "";
        if(currentCharge==2){
            currentCharge++;
            isFullyCharge = true;
            ((StormRuler)weapon).addWindSlash();
            ret += "Storm Ruler is fully charged ("+ currentCharge + "/3)";
        }else if(currentCharge < Charge.MAX_CHARGE){
            currentCharge++;
            ret += "Storm Ruler is charging (" + currentCharge + "/3)";
        }else if(currentCharge == Charge.MAX_CHARGE){
            ret += "Storm Ruler is fully charged ("+ currentCharge + "/3)";
        }
        return ret;
    }

    public String menuDescription(Actor actor) {
        return actor + " charges " + weapon.toString();
    }
}
