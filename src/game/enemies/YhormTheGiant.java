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
     * Constructor.
     *
     */
    public YhormTheGiant() {
        super("Yhorm the Giant", 'y', 500, 5000);
        inventory.add(new YhormsGreatMachete());
        behaviours.add(new AttackBehaviour());
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
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

    public boolean isEnraged() {
        return getHitPoints() < (getMaxHitPoints()/2);
    }

    public void killed(GameMap map){
        map.removeActor(this);
    }
}
