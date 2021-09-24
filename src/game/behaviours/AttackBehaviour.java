package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.Random;

public class AttackBehaviour implements Behaviour {

    private Random random = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor otherActor = destination.getActor();
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    Actions actions = new Actions();
                    actions.add(actor.getWeapon().getActiveSkill(otherActor, exit.getName()));
                    actions.add(new AttackAction(otherActor, exit.getName()));
                    return actions.get(random.nextInt(actions.size()));
                }
            }
        }
        return null;
    }
}
