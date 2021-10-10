package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponAction;
import game.Player;
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
        super("Yhorm's Great Machete", '(', 95, "slashes", 60, -1);
        allowableActions.add(new BurnGround(this));
        emberFormBool = false;
    }

    public void rageModeTest(Actor actor){
        if (actor.getClass() == YhormTheGiant.class){
           YhormTheGiant player = (YhormTheGiant)actor;
            if (player.getHitPoints() < player.getMaxHitPoints() / 2){
                hitRate += 30;
                allowableActions.add(new BurnGround(this));
                emberFormBool = true;
                System.out.println( actor + " has entered ember form");}
        } else if (actor.getClass() == Player.class){
            Player player = (Player)actor;
            if (player.getHitPoints() < player.getMaxHitPoints() / 2){
                hitRate += 30;
                allowableActions.add(new BurnGround(this));
                emberFormBool = true;
                System.out.println( actor + " has entered ember form");}
            }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (emberFormBool == false){
            rageModeTest(actor);
        }
    }

    @Override
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
