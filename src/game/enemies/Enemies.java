package game.enemies;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.FollowBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.ArrayList;

public abstract class Enemies extends Actor implements Resettable {
    private Location initialPosition;
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        registerInstance();
    }

    public void setInitialPosition(Location initialPosition) {
        this.initialPosition = initialPosition;
    }

    @Override
    public void resetInstance(GameMap map, Status status, String direction) {
        map.moveActor(this, initialPosition);
        this.heal(super.maxHitPoints);
        behaviours.remove(new FollowBehaviour(this));
    }

    @Override
    public boolean isExist(GameMap map) {
        return map.contains(this);
    }

}
