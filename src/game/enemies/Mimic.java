package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.grounds.Chest;
import game.grounds.Dirt;
import game.interfaces.Behaviour;
import game.items.Token;
import game.weapons.Kicking;

import java.util.Random;

public class Mimic extends Enemies{
    private Random random = new Random();

    public Mimic(Location initialLocation) {
        super("Mimic", 'M', 100, 200);
        setInitialPosition(initialLocation);
        behaviours.add(new AttackBehaviour());
        addCapability(Status.UNARMED);
        addCapability(Status.NOT_WEAK_TO_STORM_RULER);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for(Behaviour behaviour:behaviours){
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance(GameMap map, Status status, String direction) {
        if (status == Status.ENEMIES_KILLED) {
            map.removeActor(this);
            int prob = random.nextInt(3);
            if (prob == 0){
//                map.locationOf(this).addItem(new Token());
            } else if (prob == 1){
//                map.locationOf(this).addItem(new Token());
//                map.locationOf(this).addItem(new Token());
            } else {
//                map.locationOf(this).addItem(new Token());
//                map.locationOf(this).addItem(new Token());
//                map.locationOf(this).addItem(new Token());
            }
        } else {
            hitPoints = maxHitPoints;
            map.removeActor(this);
            initialPosition.setGround(new Chest());
        }
    }

    @Override
    public Weapon getWeapon() {
        return new Kicking();
    }
}
