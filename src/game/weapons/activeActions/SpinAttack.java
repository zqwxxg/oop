package game.weapons.activeActions;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.actions.AttackAction;
import game.enums.Status;
import game.weapons.GiantAxe;

/**
 * Active Action for weapon Giant Axe
 *
 * @see edu.monash.fit2099.engine
 * @see AttackAction
 * @see Status
 * @see GiantAxe
 */

public class SpinAttack extends WeaponAction {

    /**
     * Constructor for SpinAttack class
     * @param giantAxe the weapon that can perform this action
     */
    public SpinAttack(WeaponItem giantAxe) {
        super(giantAxe);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        int numOfTarget = 0;
        // change the damage and hit rate for spin attack
        ((GiantAxe)weapon).changeToSpinAttack();
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                // if target is attackable
                if (!destination.getActor().hasCapability(Status.UNATTACKABLE)) {
                    Actor target = destination.getActor();
                    if(target.getClass()== Player.class){
                        Action action = new AttackAction(target, exit.getName());
                        numOfTarget += 1;
                        if (numOfTarget > 1) {
                            result += System.lineSeparator();
                        }
                        result += action.execute(actor, map);}
                    }
                }
            }
        // change back the damage and hit rate for normal attack
        ((GiantAxe)weapon).changeToNormalAttack();
        if (result.equals("")) {
            result = "No enemies around";
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Spin Attack";
    }
}
