package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.SoftResetAction;
import game.enums.Status;

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
		return actor.hasCapability(Status.SOFT_RESET);
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

