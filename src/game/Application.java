package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.enemies.Skeleton;
import game.enemies.YhormTheGiant;
import game.grounds.*;
import game.weapons.StormRuler;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
				new Bonfire(), new Cemetery());

		List<String> map = Arrays.asList(
				"..++++++..+++...........................++++......+++.................+++.......",
				"........+++++..............................+++++++.................+++++........",
				"...........+++.......................................................+++++......",
				"........................................................................++......",
				"........................................................C................+++....",
				"............................+.............................................+++...",
				".............................+++.......++++.....................................",
				"................C............++.......+......................++++...............",
				".............................................................+++++++............",
				"..................................###___###...................+++...............",
				"..................................#_______#......................+++............",
				"...C.......++.....................#___B___#.......................+.............",
				".........+++......................#_______#........................++...........",
				"............+++...................####_####...............C..........+..........",
				"..............+......................................................++.........",
				"..............++.................................................++++++.........",
				"............+++...................................................++++..........",
				"+..................................................................++...........",
				"++...+++.........................................................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.........................+....++......",
				"#####___#####++++.........C............+...............................+..+.....",
				"_..._....._._#.++......................+...................................+....",
				"...+.__..+...#+++...........................................................+...",
				"...+.....+._.#.+.....+++++...++........................C.....................++.",
				"___.......___#.++++++++++++++.+++.............................................++");


		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		Player player = new Player("Unkindled (Player)", '@', 100);
		world.addPlayer(player, gameMap.at(38, 12));
		player.setLastBonfire(gameMap.at(38, 11));

//		 Place Yhorm the Giant/boss in the map
		YhormTheGiant Yhorm = new YhormTheGiant();
//		gameMap.at(6, 25).addActor(Yhorm);
		gameMap.at(38, 14).addActor(Yhorm);
		Yhorm.setInitialPosition(gameMap.at(6, 25));

		gameMap.at(37,11).addActor(new Vendor());

		Skeleton skeleton = new Skeleton();
		gameMap.at(60,20).addActor(skeleton);
		skeleton.setInitialPosition(gameMap.at(60,20));

		Skeleton skeleton2 = new Skeleton();
		gameMap.at(20,1).addActor(skeleton2);
		skeleton2.setInitialPosition(gameMap.at(20,1));

		Skeleton skeleton3 = new Skeleton();
		gameMap.at(35,2).addActor(skeleton3);
		skeleton3.setInitialPosition(gameMap.at(35,2));

		Skeleton skeleton4 = new Skeleton();
		gameMap.at(22,12).addActor(skeleton4);
		skeleton4.setInitialPosition(gameMap.at(22,12));

		Skeleton skeleton5 = new Skeleton();
		gameMap.at(30,17).addActor(skeleton5);
		skeleton5.setInitialPosition(gameMap.at(30,17));

		StormRuler stormRuler = new StormRuler();
//		gameMap.at(7,25).addItem(stormRuler);
		gameMap.at(39,12).addItem(stormRuler);

		world.run();

	}
}
