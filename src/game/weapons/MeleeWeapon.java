package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.SwapWeaponAction;
import game.enums.Abilities;

/**
 * Class representing a melee weapon
 *
 * @see edu.monash.fit2099.engine
 * @see SwapWeaponAction
 * @see Abilities
 */

public class MeleeWeapon extends WeaponItem {

    /**
     * the price of this weapon
     */
    private int price;

    /**
     * Constructor.
     *
     * @param name        name of the weapon
     * @param displayChar character to use for display when weapon is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public MeleeWeapon(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
        addCapability(Abilities.MELEE);
    }

    /**
     * Constructor.
     *
     * @param name        name of the weapon
     * @param displayChar character to use for display when weapon is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param price       the price of this weapon
     */
    public MeleeWeapon(String name, char displayChar, int damage, String verb, int hitRate, int price) {
        super(name, displayChar, damage, verb, hitRate);
        addCapability(Abilities.MELEE);
        this.price = price;
    }

    /**
     * Returns the price of this weapon.
     *
     * @return the price
     */
    public int getPrice(){return price;}

    /**
     * Creates and returns an action to swap weapon
     *
     * Overrides WeaponItem.getPickUpAction()
     *
     * @see WeaponItem#getPickUpAction(Actor)
     * @param actor an actor that will interact with this item
     * @return a new SwapWeaponAction
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new SwapWeaponAction(this);
    }

    /**
     * Returns null since we cannot drop a Weapon.
     *
     * Overrides WeaponItem.getDropAction()
     *
     * @see WeaponItem#getDropAction(Actor)
     * @param actor an actor that will interact with this item
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Returns a boolean if this weapon has the specified capability
     *
     * @param capability    The capability that this weapon is being checked for
     * @return true or false depending on if the weapon has that capability
     */
    @Override
    public boolean hasCapability(Enum<?> capability) {
        return super.hasCapability(capability);
    }
}
