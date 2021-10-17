package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * Special action for player to teleport to a destination
 *
 * @see edu.monash.fit2099.engine
 */

public class TeleportAction extends MoveActorAction {

    /**
     * Constructor.
     *
     * @param moveToLocation the destination of the move
     * @param direction String describing the destination to move in, e.g. "Anor Londo"
     */
    public TeleportAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     *
     * Overrides MoveActorAction.menuDescription()
     *
     * @see MoveActorAction#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player moves to Anor Londo"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves to " + direction;
    }
}
