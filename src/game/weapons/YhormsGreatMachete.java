package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponAction;
import game.enemies.YhormTheGiant;
import game.weapons.activeActions.BurnGround;

/**
 * Yhorm's Great Machete class
 *
 * @see edu.monash.fit2099.engine.WeaponItem
 * @see game.weapons.Axe
 */
public class YhormsGreatMachete extends Axe{

    boolean emberFormBool;
    /**
     * Constructor for Yhorm's Great Machete class
     *
     * All Yhorm's Great Machete are represented by '(', can cause 95 damage, has 60 hit rate 
     */
    public YhormsGreatMachete() {
        super("yhorm's_Great_Machete", '(', 95, "slashes", 60, -1);
        emberFormBool = false;
    }

    public void rageModeTest(YhormTheGiant yhorm){
        if (yhorm.getHitPoints() < yhorm.getMaxHitPoints() / 2){
            hitRate += 30;
            allowableActions.add(new BurnGround(this));
            emberFormBool = true;
            System.out.println("Yhorm has entered ember form");

        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (emberFormBool == false){
            rageModeTest((YhormTheGiant) actor);
        }
    }

    public WeaponAction getActiveSkill(Actor target, String direction) {
        if (emberFormBool) {
            return new BurnGround(this);
        }else{
            return null;
        }
    }

    public boolean getEmberFormBool(){
        return emberFormBool;
    }
}
