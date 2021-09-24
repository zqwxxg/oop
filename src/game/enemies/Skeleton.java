package game.enemies;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

import java.util.Random;

public class Skeleton extends Enemies {

    private Random random = new Random();
    private boolean canRevive = true;

    public Skeleton() {
        super("Skeleton", 'S', 100, 250);
        WeaponItem[] weaponList = {new Broadsword(), new GiantAxe()};
        inventory.add(weaponList[random.nextInt(weaponList.length)]);
        behaviours.add(new AttackBehaviour());
        behaviours.add(new WanderBehaviour());
        addCapability(Status.RESURRECTABLE);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                // if skeleton is following player, skip wander behaviour
                if (followBehaviourAdded && behaviour instanceof WanderBehaviour) {
                    continue;
                }
                return action;
            }
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance(GameMap map, Status status, String direction) {
        if (status == Status.ENEMIES_KILLED) {
            if (random.nextInt(2) == 0 && canRevive) {
                hitPoints = maxHitPoints;
                canRevive = false;
            } else {
                map.removeActor(this);
            }
        } else {
            hitPoints = maxHitPoints;
            map.moveActor(this, initialPosition);
            behaviours.removeIf(behaviour -> behaviour instanceof FollowBehaviour);
            followBehaviourAdded = false;
        }
    }

}
