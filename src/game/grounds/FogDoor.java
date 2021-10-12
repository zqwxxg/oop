package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.TeleportAction;
import game.enums.Abilities;

import java.util.HashMap;

public class FogDoor extends Ground {

    private HashMap<String, Location> destinationMap = new HashMap<>();

    /**
     * Constructor
     *
     * All bonfires are displayed as 'B'
     */
    public FogDoor() {
        super('=');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        // allow only actor that has this capability to enter (player and npc),
        // restrict enemies from entering
        return actor.hasCapability(Abilities.PLAYER);
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        // if the actor can perform rest action
        if (actor.hasCapability(Abilities.PLAYER)) {
            for (String key: destinationMap.keySet()) {
                actions.add(new TeleportAction(destinationMap.get(key), key));
            }
        }
        return actions;
    }

    public void addDestination(String destinationName, Location destination) {
        destinationMap.put(destinationName, destination);
    }
}
