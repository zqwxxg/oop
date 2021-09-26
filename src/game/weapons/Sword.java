package game.weapons;

import java.util.Random;

/**
 * Class for sword weapons to inherit
 *
 * @see game.weapons.MeleeWeapon
 * @see edu.monash.fit2099.engine.WeaponItem
 */
public class Sword extends MeleeWeapon {

    public static Random rand = new Random();

    /**
     * Constructor for Axe class
     * @param name name of the weapon
     * @param displayChar character to use for display when item is on the ground
     * @param damage amount of damage this weapon does
     * @param verb verb to use for this weapon
     * @param hitRate the probability/chance to hit the target
     * @param price the price of this weapon
     */
    public Sword(String name, char displayChar, int damage, String verb, int hitRate, int price){
        super(name, displayChar, damage, verb, hitRate, price);
    }

    @Override
    public int damage() {
        if (rand.nextInt(100)<= 20){
            verb = "critically strikes";
            return damage*2;
        }
        return damage;
    }
}
