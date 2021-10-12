package game.actions;

import edu.monash.fit2099.engine.*;
import game.enemies.Mimic;
import game.grounds.Chest;
import game.grounds.Dirt;
import game.items.Token;

import java.util.Random;

public class OpenChestAction extends Action {
    private Random rand = new Random();
    private Chest chest;

    public OpenChestAction(Chest chest){
        this.chest = chest;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";
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
            if (prob == 0){
//               chestLocation.addItem(new Token());
                ret += actor + " retrieved 100 souls";
            } else if (prob == 1){
//                chestLocation.addItem(new Token());
//                chestLocation.addItem(new Token());
            } else {
//                chestLocation.addItem(new Token());
//                chestLocation.addItem(new Token());
//                chestLocation.addItem(new Token());
            }
        }
        return ret;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens chest";
    }
}
