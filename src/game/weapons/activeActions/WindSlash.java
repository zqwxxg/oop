package game.weapons.activeActions;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enemies.YhormTheGiant;
import game.enums.Status;
import game.weapons.StormRuler;

public class WindSlash extends WeaponAction {

    public WindSlash(StormRuler stormRuler){super(stormRuler);};

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        ((StormRuler)weapon).changeToWindSlash();
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (!destination.getActor().hasCapability(Status.UNATTACKABLE)) {
//                    if(destination.getActor().getClass() == YhormTheGiant.class){
//                        destination.getActor()
//                    }
                    Action action = new AttackAction(destination.getActor(), exit.getName());
                    result += actor.toString() + " stuns " + destination.getActor().toString();
                    result += action.execute(actor, map);}
                }
            }
        return result;
        }

    @Override
    public String menuDescription(Actor actor) {
        return "Perform Wind Slash";
    }
}
