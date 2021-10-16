package game.weapons;

import edu.monash.fit2099.engine.*;
import java.util.Random;

public class DarkmoonLongbow extends RangedWeapon{

    private Random random = new Random();

    private boolean emberFormBool;

    private boolean abilityToggle;

    public DarkmoonLongbow() {
        super("Darkmoon Longbow", 'D', 70, "shoots", 80, 3, -1);
        emberFormBool = false;
        abilityToggle = false;
    }

//    public void rageModeTest(AldrichTheDevourer aldrich){
//        if (aldrich.getHitPoints() < aldrich.getMaxHitPoints() / 2){
//            hitRate += 30;
//            allowableActions.add(new FireArrows(this));
//            emberFormBool = true;
//            abilityToggle = false;
//            System.out.println("Aldrich has entered ember form");
//
//        }
//    }

    public void toggleAbility(){
        abilityToggle = !(abilityToggle);
    }

    public boolean getAbilityToggle(){
        return abilityToggle;
    }

    @Override
//    public void tick(Location currentLocation, Actor actor) {
//        if (emberFormBool == false){
//            rageModeTest((AldrichTheDevourer) actor);
//        }
//    }

    public WeaponAction getActiveSkill(Actor actor, String direction) {
        return null;
//        if (actor.getClass() == new AldrichTheDevourer().getClass()) {
//            if (emberFormBool) {
//                return new FireArrows(this);
//            } else {
//                return null;
//            }
//        } else if (actor.getClass() == new Player("T",'t',50).getClass()){
//            return new FireArrows(this);
//        } else { return null;}
    }

    public boolean getEmberFormBool(){
        return emberFormBool;
    }
}
