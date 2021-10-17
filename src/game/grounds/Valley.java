package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.SoftResetAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * The gorge or endless gap that is dangerous for the Player.
 *
 * @see edu.monash.fit2099.engine
 * @see SoftResetAction
 * @see Abilities
 * @see Status
 */

public class Valley extends Ground {

	/**
	 * String representing the direction of the Actor
	 */
	private String direction;

	/**
	 * Constructor.
	 *
	 * All valleys are displayed as '+'.
	 */
	public Valley() {
		super('+');
	}

	/**
	 * Allows Player to enter.
	 *
	 * Overrides Ground.canActorEnter()
	 *
	 * @see Ground#canActorEnter(Actor)
	 * @param actor the Actor to check
	 * @return true if Actor is Player, false otherwise
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		// allow only player to trigger soft reset
		return actor.hasCapability(Abilities.PLAYER);
	}

	/**
	 * Returns an Action list.
	 *
	 * Overrides Ground.allowableActions()
	 *
	 * @see Ground#allowableActions(Actor, Location, String)
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Valley from the Actor
	 * @return a collection of Actions
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		// stores the direction of the actor coming from
		this.direction = direction;
		return new Actions();
	}

	/**
	 * Allows Valley to kill Player on each turn.
	 *
	 * Overrides Ground.tick()
	 *
	 * @see Ground#tick(Location)
	 * @param location The location of the Valley
	 */
	@Override
	public void tick(Location location) {
		String prompt;
		super.tick(location);
		// if the actor steps on valley, trigger soft reset
		if (location.containsAnActor()) {
			Action resetAction = new SoftResetAction(direction);
			prompt = resetAction.execute(location.getActor(), location.map());
			System.out.println(prompt);
		}
	}
}


