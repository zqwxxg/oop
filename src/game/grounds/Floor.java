package game.grounds;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 *
 * @see edu.monash.fit2099.engine
 * @see Status
 */
public class Floor extends Ground {

	/**
	 * Constructor
	 *
	 * All floors are represented by '_'
	 */
	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		// allow only actor that has this capability to enter (player and npc),
		// restrict enemies from entering
		return actor.hasCapability(Status.ENTER_FLOOR);
	}
}
