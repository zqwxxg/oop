package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special action to remove undead from map
 *
 * @see Action
 * @see Actor
 * @see GameMap
 */
public class UndeadDieAction extends Action {

    /**
     * Allow an undead to die.
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
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * Overrides Action.menuDescription()
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor who is performing the action
     * @return a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Undead died";
    }
}
