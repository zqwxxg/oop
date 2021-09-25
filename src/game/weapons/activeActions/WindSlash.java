package game.weapons.activeActions;

import edu.monash.fit2099.engine.*;
import game.weapons.StormRuler;

public class WindSlash extends WeaponAction {

    public WindSlash(StormRuler stormRuler){super(stormRuler);};

    @Override
    public String execute(Actor actor, GameMap map) {
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Perform Wind Slash";
    }
}
