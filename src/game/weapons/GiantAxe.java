package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.weapons.activeActions.SpinAttack;

public class GiantAxe extends Axe {
    public GiantAxe() {
        super("Giant Axe", 'P', 50, "strikes", 80, 1000);
        allowableActions.add(new SpinAttack(this));
    }

    public void changeToSpinAttack() {
        verb = "spin-attacks";
        damage = 25;
        hitRate = 100;
    }

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
