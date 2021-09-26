package game.enemies;

import edu.monash.fit2099.engine.*;
import game.actions.UndeadDieAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weapons.YhormsGreatMachete;
import game.weapons.activeActions.BurnGround;

public class YhormTheGiant extends LordOfCinder{
    /**
     * return true if Yhorm is stunned (when performing Wind Slash Action)
     */
    private boolean isStunned;

    /**
     * Constructor for YhormTheGiant class
     *
     * All Yhorm the Giant are represented by an 'Y' and have 500 hit points, 5000 souls.
     */
    public YhormTheGiant() {
        super("Yhorm the Giant", 'y', 1, 5000);
        inventory.add(new YhormsGreatMachete());
        behaviours.add(new AttackBehaviour());
    }

    /**
     * setter for isStunned attribute
     */
    public void setIsStunned(){
        isStunned = true;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(isStunned){
            isStunned = false; //set isStunned to false so Yhorm is back to normal and can perform other actions
            return new DoNothingAction(); //do nothing when Yhorm is stunned
        }
        for (Behaviour behaviour : behaviours) {
            Weapon weapon = this.getWeapon();
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                if (followBehaviourAdded && behaviour instanceof WanderBehaviour) {
                    continue;
                }
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * @return true if Yhorm is in ember form
     */
    public boolean isEnraged() {
        return getHitPoints() < (getMaxHitPoints()/2);
    }

    public void killed(GameMap map){
        map.removeActor(this);
    }
}
