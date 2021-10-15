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
     */
    public RangedWeapon(String name, char displayChar, int damage, String verb, int hitRate,int range) {
        super(name, displayChar, damage, verb, hitRate);
        this.range = range;
        addCapability(Abilities.RANGED);

    }

    public RangedWeapon(String name, char displayChar, int damage, String verb, int hitRate, int range, int price) {
        super(name, displayChar, damage, verb, hitRate);
        this.range = range;
        this.price = price;
        addCapability(Abilities.RANGED);
    }

    public int getPrice(){return price;}

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new SwapWeaponAction(this);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    public int getRange(){
        return range;
    }

    @Override
    public boolean hasCapability(Enum<?> capability) {
        return super.hasCapability(capability);
    }
}
