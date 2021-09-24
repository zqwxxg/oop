package game.weapons.activeActions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import game.weapons.GiantAxe;

public class SpinAttack extends WeaponAction {

    public SpinAttack(GiantAxe giantAxe){super(giantAxe);}

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Perform Spin Attack";
    }
}
