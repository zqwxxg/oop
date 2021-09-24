package game;

import edu.monash.fit2099.engine.*;
import game.actions.BuyBroadswordAction;
import game.actions.BuyGiantAxeAction;

public class Vendor extends Actor {

    public Vendor(){
        super("Fire Keeper", 'F', 0);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        actions.add(new BuyBroadswordAction());
        actions.add(new BuyGiantAxeAction());
        return actions;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {return new DoNothingAction();}

}
