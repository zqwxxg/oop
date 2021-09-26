package game.interfaces;

import edu.monash.fit2099.engine.GameMap;
import game.ResetManager;
import game.enums.Status;

public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and items.
     *
     * @param map the map the actor is on
     * @param status the status of the action that triggers reset
     * @param direction the direction of the object that triggers reset
     */
    void resetInstance(GameMap map, Status status, String direction);

    /**
     * A useful method to clean up the list of instances in the ResetManager class
     *
     * @param map the map the actor is on
     * @return the existence of the instance in the game.
     * for example, true to keep it permanent, or false if instance needs to be removed from the reset list.
     */
    boolean isExist(GameMap map);

    /**
     * a default interface method that register current instance to the Singleton manager.
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
