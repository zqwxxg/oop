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
     * The direction of the actor that is currently acting on this bonfire
     */
    private String direction;

    /**
     * The key used in the menu to trigger this Action.
     */
    private String bonfireName;

    /**
     * Constructor
     *
     * @param direction the direction of the actor that is currently acting on this bonfire
     */
    public RestAction(String direction, String bonfireName) {
        this.direction = direction;
        this.bonfireName = bonfireName;
    }

    /**
     * Allow the actor to rest.
     *
     * Overrides Action.execute()
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(map, Status.REST, direction);
        return menuDescription(actor);
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * Overrides Action.menuDescription()
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor who is performing the action
     * @return a String. e.g. "Rest at Anor Londo's Bonfire"
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Rest at " + bonfireName;
    }
}
