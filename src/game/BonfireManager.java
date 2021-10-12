package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Location;
import game.actions.LightBonfireAction;
import game.actions.RestAction;
import game.actions.TeleportAction;
import game.enums.Abilities;
import game.grounds.Bonfire;

import java.util.HashMap;

public class BonfireManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private HashMap<Bonfire, Location> bonfireMap;

    /**
     * Constructor
     */
    public BonfireManager(){
        bonfireMap = new HashMap<>();
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public Actions getActions(Bonfire bonfire, String direction){
        Actions actions = new Actions();
        if (bonfire.hasCapability(Abilities.NOT_ACTIVATED)) {
            actions.add(new LightBonfireAction(bonfire));
        }
        else {
            actions.add(new RestAction(direction, bonfire.getName()));
            for (Bonfire otherBonfire: bonfireMap.keySet()) {
                if (bonfire != otherBonfire && !otherBonfire.hasCapability(Abilities.NOT_ACTIVATED)) {
                    actions.add(new TeleportAction(bonfireMap.get(otherBonfire), otherBonfire.getName()));
                }
            }
        }
        return actions;
    }

    /**
     * Add the Resettable instance to the list
     */
    public void addBonfire(Bonfire bonfire, Location location){
        bonfireMap.put(bonfire, location);
    }
}
