package game;

import edu.monash.fit2099.engine.*;
import game.actions.RangedAttackAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.items.EstusFlask;
import game.items.Token;
import game.weapons.Broadsword;
import game.weapons.DarkmoonLongbow;
import game.weapons.RangedWeapon;

import java.util.List;

/**
 * Class representing the Player.
 *
 * @see edu.monash.fit2099.engine
 * @see RangedAttackAction
 * @see Abilities
 * @see Status
 * @see Resettable
 * @see Soul
 * @see EstusFlask
 * @see Token
 * @see Broadsword
 * @see RangedWeapon
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
	private Location lastTokenLocation;

	/**
	 * The Token instance of last token
	 */
	private Token lastToken;

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
		inventory.add(new DarkmoonLongbow());
		//inventory.add(new Broadsword());
	}

	/**
	 * Setter for lastBonfire attribute
	 *
	 * @param lastBonfire the location of the bonfire that the player rested on
	 */
	public void setLastBonfire(Location lastBonfire) {
		this.lastBonfire = lastBonfire;
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		display.println("Unkindled (" + hitPoints + "/" + maxHitPoints + "), holding " + getWeapon() + ", " + soulCount + " souls");
		actions.add(new drinkEstusFlaskAction());

		// Handles Ranged Weapons
		if (((Item)this.getWeapon()).hasCapability(Abilities.RANGED)){
			Location here = map.locationOf(this);
			for (int counter=0; counter < Application.enemiesList.size(); counter++){
				int range = ((RangedWeapon)this.getWeapon()).getRange();
				if (map.locationOf(Application.enemiesList.get(counter)) != null) {
//					(Math.round(Math.sqrt(Math.pow(
//							map.locationOf(Application.enemiesList.get(counter)).x() - here.x()
//							, 2) + Math.pow(map.locationOf(Application.enemiesList.get(counter)).y() - here.y(), 2))) <= range || ((map.locationOf(Application.enemiesList.get(counter)).y()- here.y() > 1) && Math.round(Math.sqrt(Math.pow(
//							map.locationOf(Application.enemiesList.get(counter)).x() - here.x()
//							, 2) + Math.pow(map.locationOf(Application.enemiesList.get(counter)).y() - here.y(), 2))) <= range + 1) || ((map.locationOf(Application.enemiesList.get(counter)).x()- here.x() > 1) && Math.round(Math.sqrt(Math.pow(
//							map.locationOf(Application.enemiesList.get(counter)).x() - here.x()
//							, 2) + Math.pow(map.locationOf(Application.enemiesList.get(counter)).y() - here.y(), 2))) <= range + 1))
					if (Math.round(Math.sqrt(Math.pow(
							map.locationOf(Application.enemiesList.get(counter)).x() - here.x()
							, 2) + Math.pow(map.locationOf(Application.enemiesList.get(counter)).y() - here.y(), 2))) <= range + 1) {
						actions.add(new RangedAttackAction(Application.enemiesList.get(counter), ""));
					}
				}else{
					Application.enemiesList.remove(counter);
				}
			}
		}
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Transfer Player's souls to another Soul instance.
	 *
	 * Overrides Soul.transferSouls()
	 *
	 * @see Soul#transferSouls(Soul)
	 * @param soulObject a target soul object.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(soulCount);
		soulCount = 0;
	}

	/**
	 * Adds souls to current instance's souls.
	 *
	 * Overrides Soul.transferSouls()
	 *
	 * @see Soul#addSouls(int)
	 * @param souls number of souls to be incremented.
	 * @return transaction status. True if addition successful, otherwise False.
	 */
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

	/**
	 * Deducts souls from current instance's souls.
	 *
	 * Overrides Soul.subtractSouls()
	 *
	 * @see Soul#subtractSouls(int)
	 * @param souls number of souls to be decremented.
	 * @return transaction status. True if addition successful, otherwise False.
	 */
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

	/**
	 * Allows Player to reset abilities, attributes, and items.
	 *
	 * Overrides Resettable.resetInstance()
	 *
	 * @see game.interfaces.Resettable#resetInstance(GameMap, Status, String)
	 * @param map the map the Player is on
	 * @param status the status of the action that triggers reset
	 * @param direction the direction of the object that triggers reset
	 */
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
				if (lastTokenLocation != null) {
					// remove it from the map
					lastTokenLocation.removeItem(lastToken);
				}
				lastToken = token;
				lastTokenLocation = location;
				map.moveActor(this, lastBonfire);
			}
		}
	}

	/**
	 * A useful method to clean up the list of instances in the ResetManager class
	 *
	 * Overrides Resettable.isExist()
	 *
	 * @see Resettable#isExist(GameMap)
	 * @param map the map the Player is on
	 * @return the existence of the Player in the game.
	 * for example, true to keep it permanent, or false if Player needs to be removed from the reset list.
	 */
	@Override
	public boolean isExist(GameMap map) {
		return map.contains(this);
	}

	/**
	 * Special action for player to drink estus flask.
	 */
	private class drinkEstusFlaskAction extends Action{
		EstusFlask est;

		/**
		 * Allow Player to drink an estus flask.
		 *
		 * Overrides Action.execute()
		 *
		 * @see Action#execute(Actor, GameMap)
		 * @param actor The Player performing the action.
		 * @param map The map the Player is on.
		 * @return a description of the Action suitable for the menu
		 */
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

		/**
		 * Returns the key used in the menu to trigger this Action.
		 *
		 * @param actor The Player who is performing the action
		 * @return a String. e.g. "Drink from Estus FLask 3 charges remaining"
		 */
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
