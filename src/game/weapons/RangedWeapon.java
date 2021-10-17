package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.SwapWeaponAction;
import game.enums.Abilities;

public class RangedWeapon extends WeaponItem {
    private int price;
    private int range;
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param range       Range of the weapon
     */
    public RangedWeapon(String name, char displayChar, int damage, String verb, int hitRate,int range) {
        super(name, displayChar, damage, verb, hitRate);
        this.range = range;
        addCapability(Abilities.RANGED);

    }

    /**
     * Constructor with price
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param range       Range of the weapon
     * @param price
     */
    public RangedWeapon(String name, char displayChar, int damage, String verb, int hitRate, int range, int price) {
        super(name, displayChar, damage, verb, hitRate);
        this.range = range;
        this.price = price;
        addCapability(Abilities.RANGED);
    }

    /**
     * Returns the price of the weapon
     *
     * @return price of the weapon
     */
    public int getPrice(){return price;}

    /**
     * Method for when this weapon gets picked up
     *
     * @param actor an actor that will interact with this item
     * @return PickUpItemAction
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new SwapWeaponAction(this);
    }

    /**
     * Method for dropping this item
     *
     * @param actor an actor that will interact with this item
     * @return DropItemAction
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Returns the range of this weapon
     *
     * @return range of weapon
     */
    public int getRange(){
        return range;
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
