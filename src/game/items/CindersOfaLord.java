package game.items;

import edu.monash.fit2099.engine.*;
import game.Player;

public class CindersOfaLord extends PortableItem {

    GameMap map;
    Actor actor;
    Location location;

    public CindersOfaLord(GameMap map, Actor actor, Location location) {
        super("cindersOfALord", 'O');
        this.map = map;
        this.actor = actor;
        this.location = location;

    }

    @Override
    public void tick(Location currentLocation) {
        if (location.containsAnActor()){
            actor = location.getActor();
            if (actor.getClass() == new Player("tempPlayer", 'T', 1).getClass()){
                actor.addItemToInventory(this);
                System.out.println("Picked up " + this.name);
                location.removeItem(this);
            }
        }
    }
}

