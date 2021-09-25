package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.weapons.activeActions.SpinAttack;

/**
 * Giant Axe class
 *
 * @see edu.monash.fit2099.engine.WeaponItem
 * @see game.weapons.Axe
 */
public class GiantAxe extends Axe {
    /**
     * Constructor for Giant Axe class
     *
     * All Giant Axe are represented by 'P', can cause 50 damage, has 80 hit rate and cost 1000 souls
     */
    public GiantAxe() {
        super("Giant Axe", 'P', 50, "strikes", 80, 1000);
        allowableActions.add(new SpinAttack(this));
    }

    /**
     * set the attributes to specific values when performing Spin Attack Action
     */
    public void changeToSpinAttack() {
        verb = "spin-attacks";
        damage = 25;
        hitRate = 100;
    }

    /**
     * set the attributes back to normal condition when performing Normal Attack Action
     */
    public void changeToNormalAttack() {
        verb = "strikes";
        damage = 50;
        hitRate = 100;
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new SpinAttack(this);
    }
}
