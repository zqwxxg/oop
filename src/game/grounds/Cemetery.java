package game.grounds;

import edu.monash.fit2099.engine.*;
import game.enemies.Undead;
import game.enums.Status;

import java.util.Random;

/**
 * Class representing a cemetery
 *
 * @see edu.monash.fit2099.engine
 * @see Undead
 * @see Status
 * @see Random
 */

public class Cemetery extends Ground {

    /**
     * A random number generator
     */
    private Random random = new Random();

    /**
     * Constructor
     *
     * All cemeteries are displayed as 'C'
     */
    public Cemetery() {
        super('C');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        // allow only undead to be spawned here, other actors cannot enter
        return actor.hasCapability(Status.SPAWN_UNDEAD);
    }

    @Override
    public void tick(Location location) {
        // 25/100 = 1/4
        // draw numbers from 0 to 3 (excluding 4). Therefore, the probability of drawing 0 is equal to 25%
        boolean pass = random.nextInt(4) == 0;

        // if the test passed and there is no undead spawned earlier occupying this place, then spawn undead
        if (pass && !location.containsAnActor()) {
            location.addActor(new Undead());
        }
    }
}
