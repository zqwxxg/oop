package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.interfaces.Behaviour;
import game.weapons.StormRuler;
import game.weapons.YhormsGreatMachete;
import game.weapons.activeActions.WindSlash;

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
        super("Yhorm the Giant", 'y', 500, 5000);
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
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        Weapon weapon = otherActor.getWeapon();
        if(weapon.getClass()== StormRuler.class){ //allow Player to perform Wind Slash Action
            if(((StormRuler)weapon).getCharge().getIsFullyCharge()){
                actions.add(new WindSlash((StormRuler)weapon));
                return actions;
            }
        }
        return actions;
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
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * @return true if Yhorm is in ember form
     */
    public boolean isEnraged() {
        ((YhormsGreatMachete)getWeapon()).rageModeTest(this);
        return getHitPoints() < (getMaxHitPoints()/2);
    }

    public void killed(GameMap map){
        map.removeActor(this);
    }
}
