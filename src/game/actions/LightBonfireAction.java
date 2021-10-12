package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Abilities;
import game.grounds.Bonfire;

public class LightBonfireAction extends Action {

    private Bonfire bonfire;

    public LightBonfireAction(Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        bonfire.removeCapability(Abilities.NOT_ACTIVATED);
        return bonfire.getName() + " lit";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " lights " + bonfire.getName();
    }
}
