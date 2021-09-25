package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.RestAction;
import game.enums.Status;

/**
 * Class representing a bonfire
 *
 * @see edu.monash.fit2099.engine
 * @see RestAction
 * @see Status
 */

public class Bonfire extends Ground {

    /**
     * Constructor
     *
     * All bonfires are displayed as 'B'
     */
    public Bonfire() {
        super('B');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        // if the actor can perform rest action
        if (actor.hasCapability(Status.REST)) {
            actions.add(new RestAction(direction, "b"));
        }
        return actions;
    }
}
