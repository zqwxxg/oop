package game.weapons;

import edu.monash.fit2099.engine.*;

/**
 * Class representing darkmoon longbow
 *
 * @see edu.monash.fit2099.engine
 */

public class DarkmoonLongbow extends RangedWeapon{

    /**
     * Constructor
     */
    public DarkmoonLongbow() {
        super("Darkmoon Longbow", 'D', 70, "shoots", 80, 3, -1);
    }

    /**
     * Returns the active skill of the bow
     *
     * @param actor the actor wielding the bow
     * @param direction the direction of target, e.g. "north"
     * @return the skill of this weapon
     */
    public WeaponAction getActiveSkill(Actor actor, String direction) {
        return null;
    }

}
