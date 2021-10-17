package game.grounds;

import edu.monash.fit2099.engine.*;

import game.actions.OpenChestAction;
import game.enums.Abilities;

/**
 * Class representing a Chest
 *
 * @see edu.monash.fit2099.engine.Ground
 * @see OpenChestAction
 */
public class Chest extends Ground {

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
        Actions actions = new Actions();
        if (actor.hasCapability(Abilities.PLAYER)) {
            actions.add(new OpenChestAction(this));
        }
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
