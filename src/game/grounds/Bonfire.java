package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.RestAction;
import game.enums.Status;

public class Bonfire extends Ground {

    public Bonfire() {
        super('B');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor.hasCapability(Status.REST)) {
            actions.add(new RestAction(direction, "b"));
        }
        return actions;
    }
}
