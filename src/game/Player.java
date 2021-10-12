package game;

import edu.monash.fit2099.engine.*;
import game.actions.OpenChestAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.items.EstusFlask;
import game.items.Token;
import game.weapons.Broadsword;

import java.util.List;

/**
 * Class representing the Player.
 *
 * @see edu.monash.fit2099.engine
 * @see Abilities
 * @see Status
 * @see Resettable
 * @see Soul
 * @see EstusFlask
 * @see Token
 * @see Broadsword
 * @see List
 */
public class Player extends Actor implements Soul, Resettable {

	/**
	 * The menu that is displayed to the user
	 */
	private final Menu menu = new Menu();

	/**
	 * The location of the last bonfire that the player rested on
	 */
	private Location lastBonfire;

	/**
	 * The count of souls
	 */
	private int soulCount;

	/**
	 * The location of the last token
	 */
	private Location lastToken;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		soulCount = 0;
		this.addCapability(Abilities.PLAYER);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.ENTER_FLOOR);
		registerInstance();
		inventory.add(new EstusFlask("Estus Flask", 'e', false));
		inventory.add(new Broadsword());
	}

	/**
	 * Setter for lastBonfire attribute
	 *
	 * @param lastBonfire the location of the bonfire that the player rested on
	 */
	public void setLastBonfire(Location lastBonfire) {
		this.lastBonfire = lastBonfire;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		display.println("Unkindled (" + hitPoints + "/" + maxHitPoints + "), holding " + getWeapon() + ", " + soulCount + " souls");
		actions.add(new drinkEstusFlaskAction());
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(soulCount);
		soulCount = 0;
	}

	@Override
	public boolean addSouls(int souls) {
		if (souls >= 0) {
			soulCount += souls;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean subtractSouls(int souls) {
		if (soulCount >= souls && souls >= 0) {
			soulCount -= souls;
			return true;
		}
		else {
			return false;
		}

	}

	@Override
	public void resetInstance(GameMap map,Status status, String direction) {
		hitPoints = maxHitPoints;
		for (Item item: getInventory()) {
			if (item.hasCapability(Abilities.ESTUS_FLASK)) {
				((EstusFlask) item).resetChargeCount();
			}
		}

		Location location = map.locationOf(this);
		int x = location.x();
		int y = location.y();

		switch (status) {
			case REST -> {
				// modify the location according to the direction given to the latest bonfire's location
				// if direction is not null, the player rested at the adjacent square of bonfire
				if (direction != null) {
					switch (direction) {
						// bonfire is at north of the location that player rested on
						case "North" -> y -= 1;
						// bonfire is at north-east of the location that player rested on
						case "North-East" -> {
							x += 1;
							y -= 1;
						}
						// bonfire is at east of the location that player rested on
						case "East" -> x += 1;
						// bonfire is at south-east of the location that player rested on
						case "South-East" -> {
							x += 1;
							y += 1;
						}
						// bonfire is at south of the location that player rested on
						case "South" -> y += 1;
						// bonfire is at south-west of the location that player rested on
						case "South-West" -> {
							x -= 1;
							y += 1;
						}
						// bonfire is at west of the location that player rested on
						case "West" -> x += 1;
						// bonfire is at north-west of the location that player rested on
						case "North-West" -> {
							x -= 1;
							y -= 1;
						}
					}
					location = map.at(x, y);
				}
				lastBonfire = location;
			}
			case SOFT_RESET -> {
				// modify the location according to the dying place at valley
				// if direction is not null, the player died from falling to valley,
				// else, the player is killed by enemies
				if (direction != null) {
					switch (direction) {
						// to place soul at south of valley
						case "North" -> y += 1;
						// to place soul at south-west of valley
						case "North-East" -> {
							x -= 1;
							y += 1;
						}
						// to place soul at west of valley
						case "East" -> x -= 1;
						// to place soul at north-west of valley
						case "South-East" -> {
							x -= 1;
							y -= 1;
						}
						// to place soul at north of valley
						case "South" -> y -= 1;
						// to place soul at north-east of valley
						case "South-West" -> {
							x += 1;
							y -= 1;
						}
						// to place soul at east of valley
						case "West" -> x += 1;
						// to place soul at south-east of valley
						case "North-West" -> {
							x += 1;
							y += 1;
						}
					}
					location = map.at(x, y);
				}
				Token token = new Token(0);
				transferSouls(token);
				location.addItem(token);
				// if the location of last token is not null, which means token has been placed on map,
				if (lastToken != null) {
					// remove it from the map
					lastToken.removeItem(token);
				}
				lastToken = location;
				map.moveActor(this, lastBonfire);
			}
		}
	}

	@Override
	public boolean isExist(GameMap map) {
		return map.contains(this);
	}

	private class drinkEstusFlaskAction extends Action{
		EstusFlask est;
		@Override
		public String execute(Actor actor, GameMap map) {

			List<Item> tempList = getInventory();
			for (int counter = 0; counter < tempList.size(); counter++){
				if (tempList.get(counter).hasCapability(Abilities.ESTUS_FLASK)){
					est = (EstusFlask) inventory.get(counter);
					break;
				}


			}
			if (est.getChargeCount() <= 0){
				return "no charges remaining";
			} else {
				heal((getMaxHitPoints()/100)*40);
				est.setChargeCount(est.getChargeCount() - 1);
				return "Drank Estus Flask " + est.getChargeCount()+ " charges remaining";
			}

		}

		@Override
		public String menuDescription(Actor actor) {
			List<Item> tempList = getInventory();
			for (int counter = 0; counter < tempList.size(); counter++){
				if (tempList.get(counter).hasCapability(Abilities.ESTUS_FLASK)) {
					est = (EstusFlask) inventory.get(counter);
					break;
				}
			}
			return "Drink from Estus Flask " + est.getChargeCount() + " charges remaining";
		}
	}

	/**
	 * Returns max hit points of the player
	 *
	 * @return An integer that represents maxHitPoints
	 */
	public int getMaxHitPoints(){
		return maxHitPoints;
	}

	/**
	 * Returns hit points of the player
	 *
	 * @return An integer that represents hitPoints
	 */
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * Increases max hit points of the player
	 *
	 * @param points the points that adds on max hit points
	 */
	public void increaseMaxHitPoints(int points){
		maxHitPoints += points;
	}

	/**
	 * Setter for hitPoints attribute
	 *
	 * @param hitPoints the hit points of player
	 */
	public void setHitPoints(int hitPoints) {
		if (hitPoints >= 0 && hitPoints <= maxHitPoints) {
			this.hitPoints = hitPoints;
		}
	}
}
