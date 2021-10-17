package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.TeleportAction;
import game.enums.Abilities;
import game.enums.Status;

import java.util.HashMap;

/**
 * A class that represents a fog door.
 *
 * @see edu.monash.fit2099.engine
 * @see TeleportAction
 * @see Abilities
 * @see Status
 */

public class FogDoor extends Ground {

    /**
     * A hash map that stores String as key and Location as value
     */
    private HashMap<String, Location> destinationMap = new HashMap<>();

    /**
     * Constructor.
     *
     * All fog doors are displayed as '='.
     */
    public FogDoor() {
        super('=');
    }

    /**
     * Allows Player to enter.
     *
     * Overrides Ground.canActorEnter()
     *
     * @see Ground#canActorEnter(Actor)
     * @param actor the Actor to check
     * @return true if Actor is Player, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Abilities.PLAYER);
    }

    /**
     * Returns an Action list.
     *
     * Overrides Ground.allowableActions()
     *
     * @see Ground#allowableActions(Actor, Location, String)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the FogDoor from the Actor
     * @return a collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor.hasCapability(Abilities.PLAYER)) {
            for (String key: destinationMap.keySet()) {
                actions.add(new TeleportAction(destinationMap.get(key), key));
            }
        }
        return actions;
    }

    /**
     * Adds a destination to the current FogDoor.
     * 
     * @param destinationName the name of the destination
     * @param destination the location of the destination
     */
    public void addDestination(String destinationName, Location destination) {
        destinationMap.put(destinationName, destination);
    }
}
