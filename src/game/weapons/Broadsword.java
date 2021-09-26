package game.weapons;

import game.actions.AttackAction;

/**
 * Broadsword class
 *
 * @see edu.monash.fit2099.engine.WeaponItem
 * @see game.weapons.Sword
 */
public class Broadsword extends Sword{

    /**
     * Constructor for Broadsword class
     *
     * All Broadsword are represented by '/', can cause 30 damage, has 80 hit rate and cost 500 souls
     */
    public Broadsword(){
        super("Broadsword", '/', 30, "slash", 80, 500);
    }
}
