package game.weapons.activeActions;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;
import game.weapons.GiantAxe;

public class SpinAttack extends WeaponAction {

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
                    Action action = new AttackAction(destination.getActor(), exit.getName());
                    numOfTarget += 1;
                    if (numOfTarget > 1) {
                        result += System.lineSeparator();
                    }
                    result += action.execute(actor, map);
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
        return "Perform Spin Attack";
    }
}
