package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Abilities;
import game.grounds.Bonfire;

/**
 * Special action for player to light a bonfire
 *
 * @see edu.monash.fit2099.engine
 * @see Abilities
 * @see Bonfire
 */

public class LightBonfireAction extends Action {

    /**
     * The bonfire that is going to be lit
     */
    private Bonfire bonfire;

    /**
     * Constructor.
     *
     * @param bonfire the bonfire that is going to be lit
     */
    public LightBonfireAction(Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    /**
     * Allow actor to light a bonfire.
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
        bonfire.removeCapability(Abilities.NOT_ACTIVATED);
        return bonfire.getName() + " lit";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @param actor The actor who is performing the action
     * @return a String. e.g. "Player lights Anor Londo's Bonfire"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " lights " + bonfire.getName();
    }
}
