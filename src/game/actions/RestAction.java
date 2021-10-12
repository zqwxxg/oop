package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ResetManager;
import game.enums.Status;

/**
 * Special action for player to rest
 *
 * @see Action
 * @see Actor
 * @see GameMap
 * @see ResetManager
 * @see Status
 */

public class RestAction extends Action {

    /**
     * The direction of bonfire
     */
    private String direction;

    /**
     * The key used in the menu to trigger this Action.
     */
    private String bonfireName;

    /**
     * Constructor
     *
     * @param direction the direction of bonfire
     */
    public RestAction(String direction, String bonfireName) {
        this.direction = direction;
        this.bonfireName = bonfireName;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(map, Status.REST, direction);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Rest at " + bonfireName;
    }
}
