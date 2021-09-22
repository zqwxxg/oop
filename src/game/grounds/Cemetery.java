package game.grounds;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.Undead;

import java.util.Random;

public class Cemetery extends Ground {

    public Cemetery() {
        super('C');
    }

    @Override
    public void tick(Location location) {
        Random rand = new Random();
        // 25/100 = 1/4
        // draw numbers from 0 to 3 (excluding 4). Therefore, the probability of drawing 0 is equal to 25%
        boolean pass = rand.nextInt(4) == 0;

        if (pass && !location.containsAnActor()) {
            location.addActor(new Undead("Undead"));
        }
    }
}
