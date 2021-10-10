package game.weapons;

public class Bow extends MeleeWeapon {
    /**
     * Constructor for Axe class
     * @param name name of the weapon
     * @param displayChar character to use for display when item is on the ground
     * @param damage amount of damage this weapon does
     * @param verb verb to use for this weapon
     * @param hitRate the probability/chance to hit the target
     * @param price the price of this weapon
     */
    public Bow(String name, char displayChar, int damage, String verb, int hitRate, int price){
        super(name, displayChar, damage, verb, hitRate, price);
    }
}
