package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponAction;
import game.enemies.Enemies;
import game.enemies.YhormTheGiant;
import game.weapons.activeActions.BurnGround;
import game.weapons.activeActions.SpinAttack;

public class YhormsGreatMachete extends MeleeWeapon{

    boolean emberFormBool;
    /**
     * Constructor.
     *
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
        return new BurnGround(this);
    }

    public boolean getEmberFormBool(){
        return emberFormBool;
    }
}
