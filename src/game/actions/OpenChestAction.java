package game.actions;

import edu.monash.fit2099.engine.*;
import game.enemies.Mimic;
import game.grounds.Chest;
import game.grounds.Dirt;
import game.items.Token;

import java.util.Random;

/**
 * Class to open chest
 *
 * @see edu.monash.fit2099.engine.Action
 * @see Mimic
 * @see Chest
 * @see Dirt
 * @see Token
 */
public class OpenChestAction extends Action {
    /**
     * A random number generator
     */
    private Random rand = new Random();

    /**
     * The chest we are opening
     */
    private Chest chest;

    /**
     * Constructor
     *
     * @param chest the chest that we want to open
     */
    public OpenChestAction(Chest chest){
        this.chest = chest;
    }

    /**
     * Allow player to open the chest
     *
     * Overrides Action.execute()
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location chestLocation = map.locationOf(actor);
        for(Exit exit : map.locationOf(actor).getExits()){
            Location destination = exit.getDestination();
            if(destination.getGround().equals(chest)){
                int x = destination.x();
                int y = destination.y();
                chestLocation = map.at(x,y);
            }
        }
        if(rand.nextInt(100) < 50){
            Mimic mimic = new Mimic(chestLocation);
            chestLocation.addActor(mimic);
            chestLocation.setGround(new Dirt());
        }else{
            int prob = rand.nextInt(3);
            chestLocation.setGround(new Dirt());
            if (prob == 0){
                chestLocation.addItem(new Token(100));
            } else if (prob == 1){
                chestLocation.addItem(new Token(100));
                chestLocation.addItem(new Token(100));
            } else {
                chestLocation.addItem(new Token(100));
                chestLocation.addItem(new Token(100));
                chestLocation.addItem(new Token(100));
            }
        }
        return actor + " opened chest";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * Overrides Action.menuDescription()
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor who is performing the action
     * @return a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens chest";
    }
}
