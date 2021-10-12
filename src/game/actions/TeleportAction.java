package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class TeleportAction extends MoveActorAction {

    public TeleportAction(Location moveToLocation, String direction, String hotKey) {
        super(moveToLocation, direction, hotKey);
    }

    public TeleportAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves to " + direction;
    }
}
