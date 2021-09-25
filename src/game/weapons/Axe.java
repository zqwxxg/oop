package game.weapons;

/**
 * Class for axe weapons to inherit
 *
 * @see game.weapons.MeleeWeapon
 * @see edu.monash.fit2099.engine.WeaponItem
 */
public class Axe extends MeleeWeapon {

    /**
     * Constructor for Axe class
     * @param name name of the weapon
     * @param displayChar character to use for display when item is on the ground
     * @param damage amount of damage this weapon does
     * @param verb verb to use for this weapon
     * @param hitRate the probability/chance to hit the target
     * @param price the price of this weapon
     */
    public Axe(String name, char displayChar, int damage, String verb, int hitRate, int price){
        super(name, displayChar, damage, verb, hitRate, price);
    }


    /**
     * Constructor for Axe class
     * @param name name of the weapon
     * @param displayChar character to use for display when item is on the ground
     * @param damage amount of damage this weapon does
     * @param verb verb to use for this weapon
     * @param hitRate the probability/chance to hit the target
     */
    public Axe(String name, char displayChar, int damage, String verb, int hitRate){
        super(name, displayChar, damage, verb, hitRate);
    }
}
