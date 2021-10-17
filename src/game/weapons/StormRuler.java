package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.weapons.activeActions.Charge;
import game.weapons.activeActions.WindSlash;

/**
 * Storm Ruler class
 *
 * @see edu.monash.fit2099.engine.WeaponItem
 * @see Charge
 * @see WindSlash
 * @see game.weapons.Sword
 */
public class StormRuler extends Sword{

    /**
     * Charge action that Storm Ruler can perform
     */
    private Charge charge = new Charge(this);

    /**
     * Wind Slash action that Storm Ruler can perform
     */
    private WindSlash windSlash = new WindSlash(this);

    /**
     * Constructor for Storm Ruler class
     *
     * All Storm Ruler are represented by '7', can cause 70 damage, has 60 hit rate and cost 2000 souls
     */
    public StormRuler(){
        super("Storm Ruler", '7', 70, "slash", 60, 2000);
    }

    /**
     * getter for charge attribute
     * @return Charge of the Storm Ruler
     */
    public Charge getCharge() {
        return charge;
    }
    
    /**
     * double the damage when performing Wind Slash
     * @return Damage of Wind Slash
     */
    public int WindSlashDamage(){
        return damage*2;
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
        if(charge.getIsFullyCharge()){
            allowableActions.remove(charge);
            allowableActions.add(windSlash);
            return windSlash;
        }else
            {allowableActions.add(charge);
                return charge;}
    }

    /**
     * allow to perform Charge Action after picking up Storm Ruler
     */
    public void afterPickUp(){
        allowableActions.add(charge);
    }

    /**
     * remove Charge Action when Storm Ruler is fully charged
     */
    public void removeCharge(){
        if(charge.getIsFullyCharge()){
            allowableActions.remove(charge);
        }
    }

    /**
     * allow to recharge the Storm Ruler when Wind Slash Action has been performed
     */
    public void addCharge(){
        charge = new Charge(this);
        allowableActions.add(charge);
    }


}
