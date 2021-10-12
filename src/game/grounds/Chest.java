package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.OpenChestAction;

public class Chest extends Ground {
    private Action openChestAction = new OpenChestAction(this);

    public Chest() { super('?'); }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        actions.add(openChestAction);
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}