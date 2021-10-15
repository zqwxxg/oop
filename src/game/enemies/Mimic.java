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
            int prob = random.nextInt(3);
            if (prob == 0){
                map.locationOf(this).addItem(new Token(100));
            } else if (prob == 1){
                map.locationOf(this).addItem(new Token(100));
                map.locationOf(this).addItem(new Token(100));
            } else {
                map.locationOf(this).addItem(new Token(100));
                map.locationOf(this).addItem(new Token(100));
                map.locationOf(this).addItem(new Token(100));
            }
            map.removeActor(this);
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
