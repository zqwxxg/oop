package game.actions;

import edu.monash.fit2099.engine.*;
import game.ResetManager;
import game.enums.Status;

public class SoftResetAction extends Action {
    protected Ground target;
    protected String direction;

    public SoftResetAction(String direction) {
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(map, Status.SOFT_RESET, direction);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "YOU DIED";
    }
}
