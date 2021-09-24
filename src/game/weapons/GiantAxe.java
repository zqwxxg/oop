package game.weapons;

import edu.monash.fit2099.engine.Action;
import game.weapons.activeActions.SpinAttack;

import java.util.List;

public class GiantAxe extends Axe {

    public GiantAxe(){
        super("Giant Axe", 'P', 50, "hack", 80, 1000);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = super.getAllowableActions();
        actions.add(new SpinAttack(this));
        return actions;
    }
}
