package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.Player;
import game.enemies.YhormTheGiant;
import game.weapons.activeActions.BurnGround;

/**
 * Yhorm's Great Machete class
 *
 * @see edu.monash.fit2099.engine
 * @see Player
 * @see YhormTheGiant
 * @see BurnGround
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
        emberFormBool = false;
    }

    /**
     * Checks if the Actor has entered rage mode.
     *
     * @param actor the Actor holding this weapon
     */
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

    /**
     * Checks if this weapon is in ember form each turn.
     *
     * Overrides Axe.tick()
     *
     * @see Axe#tick(Location)
     * @param currentLocation The location of the actor carrying this weapon.
     * @param actor The actor carrying this weapon.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (!emberFormBool){
            rageModeTest(actor);
        }
    }

    /**
     * Return the active skill from the weapon that will be used against one target.
     *
     * @see WeaponItem#allowableActions for a self-direction skill instead of using this method (recommendation)
     * @param target the target actor
     * @param direction the direction of target, e.g. "north"
     * @return null by default because a weapon doesn't have any active skill. Otherwise, return a WeaponAction instance.
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if (emberFormBool) {
            return new BurnGround(this);
        }else{
            return null;
        }
    }
}
