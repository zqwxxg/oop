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
 * @see RestAction
 * @see Status
 */

public class Bonfire extends Ground {

    private BonfireManager bonfireManager;
    private String name;
    /**
     * Constructor
     *
     * All bonfires are displayed as 'B'
     */
    public Bonfire(String name, boolean activated, BonfireManager bonfireManager) {
        super('B');
        this.name = name;
        this.bonfireManager = bonfireManager;
        if (!activated) {
            addCapability(Abilities.NOT_ACTIVATED);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor.hasCapability(Abilities.PLAYER)) {
            actions = bonfireManager.getActions(this, direction);
        }
        return actions;
    }
}
