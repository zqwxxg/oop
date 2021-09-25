package game.actions;

import edu.monash.fit2099.engine.*;
import game.ResetManager;
import game.enums.Status;

/**
 * Special action for player to rest
 *
 * @see edu.monash.fit2099.engine
 * @see ResetManager
 * @see Status
 */

public class SoftResetAction extends Action {

    /**
     * The direction of the threat that causes the actor to die
     */
    protected String direction;

    /**
     * Constructor
     *
     * @param direction the direction of the threat that causes the actor to die
     */
    public SoftResetAction(String direction) {
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(map, Status.SOFT_RESET, direction);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "YOU DIED";
    }
}
