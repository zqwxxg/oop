package game.weapons;

import edu.monash.fit2099.engine.Action;

import java.util.List;

public class Broadsword extends Sword{

    public Broadsword(){
        super("Broadsword", '/', 30, "slash", 80, 500);
    }

    @Override
    public List<Action> getAllowableActions() {
        return super.getAllowableActions();
    }
}
