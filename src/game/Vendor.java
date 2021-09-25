package game;

import edu.monash.fit2099.engine.*;
import game.actions.BuyBroadswordAction;
import game.actions.BuyGiantAxeAction;
import game.actions.IncreaseMaxHpAction;
import game.enums.Status;

public class Vendor extends Actor {

    public Vendor(){
        super("Fire Keeper", 'F', 0);
        addCapability(Status.UNATTACKABLE);
        addCapability(Status.ENTER_FLOOR);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        actions.add(new BuyBroadswordAction());
        actions.add(new BuyGiantAxeAction());
        actions.add(new IncreaseMaxHpAction());
        return actions;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {return new DoNothingAction();}

}
