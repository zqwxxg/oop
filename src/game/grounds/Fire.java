package game.grounds;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.YhormTheGiant;

/**
 * Class representing a fire
 *
 * @see edu.monash.fit2099.engine
 * @see Location
 * @see YhormTheGiant
 */
public class Fire extends Ground {

    /**
     * The number of turns that a fire can exist
     */
    private int turnCounter;

    /**
     * Constructor.
     *
     * All fires are displayed as 'V'.
     */
    public Fire() {
        super('V');
        turnCounter = 0;
    }

    /**
     * Allows Fire to stay on map for three turns and deal damage to an Actor standing on it.
     *
     * Overrides Ground.tick()
     *
     * @see Ground#tick(Location)
     * @param location The location of the Fire
     */
    @Override
    public void tick(Location location) {
        if (turnCounter == 3){
            location.setGround(new Dirt());
        }
        else if (location.containsAnActor() && location.getActor().getClass() != YhormTheGiant.class){
            location.getActor().hurt(25);
            System.out.println(location.getActor() + " took 25 damage from fire");
        }
        turnCounter++;
    }

}
