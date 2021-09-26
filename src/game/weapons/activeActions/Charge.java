package game.weapons.activeActions;

import edu.monash.fit2099.engine.*;
import game.enemies.YhormTheGiant;
import game.weapons.StormRuler;

/**
 * Active Action for weapon Storm Ruler
 *
 * @see edu.monash.fit2099.engine.WeaponAction
 */
public class Charge extends WeaponAction {
    /**
     * the current charge of the weapon
     * return true if Storm Ruler is fully charge
     * the maximum charge that a Storm Ruler can have
     */
    private int currentCharge = 0;
    private boolean isFullyCharge = false;
    private static final int MAX_CHARGE = 3;

    /**
     * Constructor for Charge class
     * @param stormRuler the weapon that can perform this action
     */
    public Charge(StormRuler stormRuler){
        super(stormRuler);
    }

    /**
     * @return true if the Storm Ruler is fully charged
     */
    public boolean getIsFullyCharge(){
        return isFullyCharge;
    }

    public String execute(Actor actor, GameMap map) {
        menuDescription(actor);
        String ret = "";
        if(currentCharge==2){
            currentCharge++;
            isFullyCharge = true;
            ((StormRuler)weapon).removeCharge();
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
