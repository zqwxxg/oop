package game.behaviours;

import edu.monash.fit2099.engine.*;
import edu.monash.fit2099.engine.Action;
import game.actions.AttackAction;
import game.enemies.Enemies;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weapons.RangedWeapon;

import javax.swing.*;
import java.util.Random;

/**
 * A class that figures out a AttackAction that will causes the actor to attack the target Actor.
 *
 * @see edu.monash.fit2099.engine
 * @see AttackAction
 * @see Status
 * @see Behaviour
 * @see Random
 */

public class AttackBehaviour implements Behaviour {

    /**
     * A random number generator
     */
    private Random random = new Random();

    /**
     * Gets an action based on the actor and map
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return An Action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.UNARMED) || !((Item)actor.getWeapon()).hasCapability(Abilities.RANGED)) {
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    Actor otherActor = destination.getActor();
                    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        Actions actions = new Actions();
                        // add active skills of the actor's weapon
                        actions.add(actor.getWeapon().getActiveSkill(otherActor, exit.getName()));
                        // add normal attack
                        actions.add(new AttackAction(otherActor, exit.getName()));
                        // randomly choose from using active skill to attack or perform normal attack
                        return actions.get(random.nextInt(actions.size()));
                    }
                }
            }
        }else{ // if weapon is ranged
            // This check is only for enemies with ranged weapons
            //loop through range and see if actor is there
            Location currentLocation = map.locationOf(actor);
            int startX = currentLocation.x() - ((RangedWeapon)actor.getWeapon()).getRange();
            int startY = currentLocation.y() - ((RangedWeapon)actor.getWeapon()).getRange();
            int endX = currentLocation.x() + ((RangedWeapon)actor.getWeapon()).getRange();
            int endY = currentLocation.y() + ((RangedWeapon)actor.getWeapon()).getRange();
            for (int counterY = startY; counterY <= endY; counterY++){
                for (int counterX = startX; counterX <= endX; counterX++){
                    Location here = new Location(map, counterX, counterY);
                    if (here.containsAnActor()){
                        Actor otherActor = here.getActor();
                        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                            if ((counterY == startY ||  counterY == endY) || (counterX == startX || counterX == endX)){
                                ((Enemies)actor).addBehaviour(new FollowBehaviour(otherActor));
                            }
                            Actions actions = new Actions();
                            // add active skills of the actor's weapon
                            actions.add(actor.getWeapon().getActiveSkill(otherActor, ""));
                            // add normal attack
                            actions.add(new AttackAction(otherActor, ""));
                            // randomly choose from using active skill to attack or perform normal attack
                            return actions.get(random.nextInt(actions.size()));
                        }
                    }
                }
            }
        }
        return null;
    }
}
