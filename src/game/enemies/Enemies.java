package game.enemies;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.behaviours.FollowBehaviour;
import game.actions.AttackAction;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

public abstract class Enemies extends Actor implements Soul, Resettable{

    protected boolean followBehaviourAdded = false;
    protected Location initialPosition;
    protected ArrayList<Behaviour> behaviours = new ArrayList<>();
    protected int soulCount;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints, int soulCount) {
        super(name, displayChar, hitPoints);
        this.soulCount = soulCount;
        registerInstance();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getMaxHitPoints(){
        return maxHitPoints;
    }

    public void setInitialPosition(Location initialPosition) {
        this.initialPosition = initialPosition;
    }

    public Location getInitialPosition(){return initialPosition;}

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
            if (!followBehaviourAdded) {
                behaviours.add(new FollowBehaviour(otherActor));
                followBehaviourAdded = true;
            }
        }
        return actions;
    }


    @Override
    public boolean isExist(GameMap map) {
        return map.contains(this);
    }

    @Override
    public void transferSouls(Soul soulObject) {
        //TODO: transfer Player's souls to another Soul's instance.
        soulObject.addSouls(soulCount);
    }

    @Override
    public boolean addSouls(int souls) {
        if (souls >= 0) {
            soulCount += souls;
            return true;
        }
        else {
            return false;
        }
    }

}
