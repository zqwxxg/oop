package game.weapons.activeActions;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;
import game.grounds.Dirt;
import game.grounds.Fire;
import game.weapons.GiantAxe;

public class BurnGround extends WeaponAction {

    public BurnGround(WeaponItem yhormsGreatMachete){super(yhormsGreatMachete);}

    @Override
    public String execute(Actor actor, GameMap map) {
        Dirt tempDirt = new Dirt();
        String result = "";
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().getClass() == tempDirt.getClass()) {
                destination.setGround(new Fire());
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Burn the ground";
    }
}
