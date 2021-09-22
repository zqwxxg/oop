package game.grounds;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.actions.SoftResetAction;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	private String direction;

	public Valley() {
		super('+');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return actor instanceof Player;
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		this.direction = direction;
		return new Actions();
	}

	@Override
	public void tick(Location location) {
		String prompt;
		super.tick(location);
		if (location.containsAnActor()) {
			Action resetAction = new SoftResetAction(direction);
			prompt = resetAction.execute(location.getActor(), location.map());
			System.out.println(prompt);
		}
	}
}


