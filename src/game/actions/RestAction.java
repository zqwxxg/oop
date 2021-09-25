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
    protected String direction;

    /**
     * The key used in the menu to trigger this Action.
     */
    protected String hotkey;

    /**
     * Constructor
     *
     * @param direction the direction of bonfire
     * @param hotkey the key used in the menu to trigger this Action.
     */
    public RestAction(String direction, String hotkey) {
        this.direction = direction;
        this.hotkey = hotkey;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(map, Status.REST, direction);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Rest at Firelink Shrine's Bonfire";
    }

    @Override
    public String hotkey() {
        return hotkey;
    }
}
