package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ResetManager;
import game.enums.Status;

/**
 * Class representing
 */
public class RestAction extends Action {

    protected String direction;
    protected String hotkey;

    public RestAction(String direction, String hotkey) {
        this.direction = direction;
        this.hotkey = hotkey;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(map, Status.REST, direction);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Rest at Firelink Shrine's Bonfire";
    }

    @Override
    public String hotkey() {
        return hotkey;
    }
}
