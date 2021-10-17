package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Location;
import game.actions.LightBonfireAction;
import game.actions.RestAction;
import game.actions.TeleportAction;
import game.enums.Abilities;
import game.grounds.Bonfire;

import java.util.HashMap;

/**
 * A manager that stores all instances of Bonfire in a game.
 *
 * @see edu.monash.fit2099.engine
 * @see LightBonfireAction
 * @see RestAction
 * @see TeleportAction
 * @see Abilities
 * @see Bonfire
 */

public class BonfireManager {

    /**
     * A hash map that stores Bonfire as key and Location as value
     */
    private HashMap<Bonfire, Location> bonfireMap;

    /**
     * Constructor.
     */
    public BonfireManager(){
        bonfireMap = new HashMap<>();
    }

    /**
     * Returns a list of available action.
     *
     * @param bonfire the bonfire that an actor is currently acting on
     * @param direction the direction of the actor that is currently acting on this bonfire
     * @return a list of available action
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
     * Adds a bonfire instance to the bonfire manager
     *
     * @param bonfire a bonfire instance
     * @param location the location of this bonfire instance
     */
    public void addBonfire(Bonfire bonfire, Location location){
        bonfireMap.put(bonfire, location);
    }
}
