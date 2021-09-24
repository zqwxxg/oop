package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.items.EstusFlask;
import game.items.Token;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private final Menu menu = new Menu();
	private Location lastBonfire;
	private int soulCount;
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
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.REST);
		this.addCapability(Status.SOFT_RESET);
		this.addCapability(Status.ENTER_FIRELINK_SHRINE);
		registerInstance();
		inventory.add(new EstusFlask("Estus Flask", 'e', false));
		inventory.add(new Broadsword());
	}

	public void setLastBonfire(Location lastBonfire) {
		this.lastBonfire = lastBonfire;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// initialize location of bonfire
		lastBonfire = map.at(38, 11);

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
		//TODO: transfer Player's souls to another Soul's instance.
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
		if (soulCount >= souls) {
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

		// modify the location according to the direction given to the latest bonfire's location or dying place at valley
		Location location = map.locationOf(this);
		int x = location.x();
		int y = location.y();

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


		switch (status) {
			case REST -> lastBonfire = location;
			case SOFT_RESET -> {
				Token token = Token.getInstance("Token of Souls", '$');
				transferSouls(token);
				location.addItem(token);
				if (lastToken != null) {
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
		EstusFlask est = new EstusFlask("dummyFlask",'d', false);
		@Override
		public String execute(Actor actor, GameMap map) {

			for (int counter = 0; counter < inventory.size(); counter++){
				if (inventory.get(counter).getClass() == est.getClass()) {
					est = (EstusFlask) inventory.get(counter);
					break;
				}
			}
			if (est.getChargeCount() <= 0){
				return "no charges remaining";
			} else {

				setHitPoints(getHitPoints() + (getMaxHitPoints()/100)*40);
				if (getHitPoints() > getMaxHitPoints()){
					setHitPoints(getMaxHitPoints());
				}
				est.setChargeCount(est.getChargeCount() - 1);
				return "Drank Estus Flask " + est.getChargeCount()+ " charges remaining";
			}

		}

		@Override
		public String menuDescription(Actor actor) {
			for (int counter = 0; counter < inventory.size(); counter++){
				if (inventory.get(counter).getClass() == est.getClass()) {
					est = (EstusFlask) inventory.get(counter);
					break;
				}
			}
			return "Drink from Estus Flask " + est.getChargeCount() + " charges remaining";
		}
	}

	public int getMaxHitPoints(){
		return maxHitPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		if (hitPoints >= 0 && hitPoints <= 100) {
			this.hitPoints = hitPoints;
		}
	}



}
