package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.Undead;
import game.enums.Status;

import java.util.Random;

public class Cemetery extends Ground {

    private Random random = new Random();

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
