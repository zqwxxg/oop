package game.grounds;

import edu.monash.fit2099.engine.*;
import game.BonfireManager;
import game.actions.RestAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Class representing a bonfire
 *
 * @see edu.monash.fit2099.engine
 * @see BonfireManager
 * @see RestAction
 * @see Abilities
 * @see Status
 */

public class Bonfire extends Ground {

    /**
     * The bonfire manager that stores all instances of bonfire in current game
     */
    private BonfireManager bonfireManager;

    /**
     * The name of this bonfire
     */
    private String name;

    /**
     * Constructor.
     *
     * All bonfires are displayed as 'B'.
     */
    public Bonfire(String name, boolean activated, BonfireManager bonfireManager) {
        super('B');
        this.name = name;
        this.bonfireManager = bonfireManager;
        if (!activated) {
            addCapability(Abilities.NOT_ACTIVATED);
        }
    }

    /**
     * Returns the name of this bonfire.
     *
     * @return a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an Action list.
     *
     * Overrides Ground.allowableActions()
     *
     * @see Ground#allowableActions(Actor, Location, String)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Bonfire from the Actor
     * @return a collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor.hasCapability(Abilities.PLAYER)) {
            actions = bonfireManager.getActions(this, direction);
        }
        return actions;
    }
}
