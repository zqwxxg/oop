package game.grounds;

import edu.monash.fit2099.engine.*;

import game.actions.OpenChestAction;

/**
 * Class representing a Chest
 *
 * @see edu.monash.fit2099.engine.Ground
 */
public class Chest extends Ground {
    private Action openChestAction = new OpenChestAction(this);

    /***
     * Constructor.
     *
     * Chest is displayed as '?'
     */
    public Chest() { super('?'); }

    /**
     * Allows player to open the chest
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an action list that Player can interact with chest
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        actions.add(openChestAction);
        return actions;
    }

    /**
     * Allows player to step on the chest
     *
     * @param actor the Actor acting
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}