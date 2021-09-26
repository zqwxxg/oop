package game.weapons.activeActions;

import edu.monash.fit2099.engine.*;
import game.actions.WindSlashAction;
import game.enemies.YhormTheGiant;
import game.weapons.StormRuler;

/**
 * Active Action for weapon Storm Ruler
 *
 * @see edu.monash.fit2099.engine.WeaponAction
 */
public class WindSlash extends WeaponAction {

    /**
     * Constructor for WindSlash class
     * @param stormRuler the weapon that can perform this action
     */
    public WindSlash(StormRuler stormRuler){super(stormRuler);};

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                //check if the target is Yhorm
                if (destination.getActor().getClass() == YhormTheGiant.class) {
                    Action action = new WindSlashAction(destination.getActor(), exit.getName());
                    ((StormRuler)weapon).addCharge();
                    ((YhormTheGiant)destination.getActor()).setIsStunned(); //stuns Yhorm, cannot perform action for a round
                    return action.execute(actor, map);
                }else{
                    result += "Wind Slash can only perform to Yhorm The Giant";
                }
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Wind Slash" ;
    }
}
