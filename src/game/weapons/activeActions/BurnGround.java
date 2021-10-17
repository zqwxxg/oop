package game.weapons.activeActions;

import edu.monash.fit2099.engine.*;
import game.grounds.Dirt;
import game.grounds.Fire;

/**
 * Active Action for weapon Yhorm's Great Machete
 *
 * @see edu.monash.fit2099.engine.WeaponAction
 */
public class BurnGround extends WeaponAction {

    /**
     * Constructor for BurnGround class
     * @param yhormsGreatMachete the weapon that can perform this action
     */
    public BurnGround(WeaponItem yhormsGreatMachete){super(yhormsGreatMachete);}

    @Override
    public String execute(Actor actor, GameMap map) {
        Dirt tempDirt = new Dirt();
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().getClass() == tempDirt.getClass()) {
                destination.setGround(new Fire());
            }
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " burns the ground";
    }
}
