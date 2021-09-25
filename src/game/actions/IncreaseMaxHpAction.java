package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

public class IncreaseMaxHpAction extends Action {
    private String item = "Max HP modifier";
    private int price = 200;

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player)actor;
        menuDescription(actor);
        if(player.subtractSouls(price)){
            player.increaseMaxHitPoints(25);
            player.subtractSouls(price);
            return player.toString() + " maximum hit points has been increased to " + player.getMaxHitPoints();
        }
        return "Not enough souls to buy " + item;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " buys Max HP modifier (+25HP) (200 souls)";
    }
}
