package game.grounds;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.YhormTheGiant;

public class Fire extends Ground {
    private int turnCounter;
    /**
     * Constructor.
     *
     */
    public Fire() {
        super('V');
        turnCounter = 0;
    }

    @Override
    public void tick(Location location) {
        if (turnCounter == 3){
            location.setGround(new Dirt());
        }
        else if (location.containsAnActor() && location.getActor().getClass() != new YhormTheGiant().getClass()){
            location.getActor().hurt(25);
            System.out.println(location.getActor() + " took 25 damage from fire");
        }
        turnCounter++;
    }

}
