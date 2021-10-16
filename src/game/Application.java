package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.enemies.AldrichTheDevourer;
import game.enemies.Enemies;
import game.enemies.Skeleton;
import game.enemies.YhormTheGiant;
import game.grounds.*;
import game.grounds.Chest;
import game.weapons.StormRuler;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static ArrayList<Enemies> enemiesList= new ArrayList<>();

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
				new Cemetery(), new Chest());

		List<String> profaneCapitalMap = Arrays.asList(
				"..++++++..+++...........................++++......+++.................+++.......",
				"........+++++..............................+++++++.................+++++........",
				"...........+++.......................................................+++++......",
				"...............?........................................................++......",
				"........................................................C................+++....",
				"............................+.............................................+++...",
				".............................+++.......++++.?...................................",
				"................C............++.......+......................++++...............",
				".............................................................+++++++............",
				"..................................###___###...................+++...............",
				"..................................#_______#......................+++............",
				"...C.......++.....................#_______#.......................+.............",
				".........+++......................#_______#........................++...........",
				"............+++.?.................####_####...............C..........+..........",
				"..............+......................................................++.........",
				"............?.++.................................................++++++.........",
				"............+++...................................................++++..........",
				"+..................................................??..............++...........",
				"++...+++.........................................................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.......................?.+....++......",
				"#####___#####++++.........C............+..........................?....+..+.....",
				"_..._....._._#.++......................+...................................+....",
				"...+.__..+...#+++...........................................................+...",
				"...+.....+._.#.+.....+++++...++........................C.....................++.",
				"___.......___#.++++++++++++++.+++.............................................++");

		List<String> anorLondoMap = Arrays.asList(
				"..........+++................++...............................++++....",
				".........?.++...........................................++++++++......",
				"............+++..................................##_##.....++++++.....",
				"................................................._____.........++.....",
				"...................?.............................##_##..........+++...",
				".........C..........++++........................................?.....",
				"...................+++.....................++.........................",
				".................................................C....................",
				"........................................................+++++++.......",
				".........................................................++...........",
				"........................###___########################.........++.....",
				"........................#.............__......?......#...........?....",
				"........................#....................++......#................",
				".............++.........#...#.................++.....#................",
				"............+...........#............................#................",
				"........................#.?.#.....................#..#........C.......",
				".........C..............#.................__.........#................",
				".................+++....#....._.....#................#................",
				"..............+++.?.....###___########################................",
				"...............?......................................................",
				".......................................................++++...........",
				"..........................................................+++++.......");


		GameMap profaneCapital = new GameMap(groundFactory, profaneCapitalMap);
		world.addGameMap(profaneCapital);

		GameMap anorLondo = new GameMap(groundFactory, anorLondoMap);
		world.addGameMap(anorLondo);

		Player player = new Player("Unkindled (Player)", '@', 10000);
		world.addPlayer(player, profaneCapital.at(38, 12));

		BonfireManager bonfireManager = new BonfireManager();

		Bonfire firelinkBonfire = new Bonfire("Firelink Shrine's Bonfire", true, bonfireManager);
		profaneCapital.at(38, 11).setGround(firelinkBonfire);
		bonfireManager.addBonfire(firelinkBonfire, profaneCapital.at(38, 11));
		player.setLastBonfire(profaneCapital.at(38, 11));

		Bonfire anorBonfire = new Bonfire("Anor Londo's Bonfire", false, bonfireManager);
		anorLondo.at(51, 3).setGround(anorBonfire);
		bonfireManager.addBonfire(anorBonfire, anorLondo.at(51, 3));

		FogDoor capitalFogDoor = new FogDoor();
		profaneCapital.at(38, 25).setGround(capitalFogDoor);
		capitalFogDoor.addDestination("Anor Londo", anorLondo.at(45, 0));

		FogDoor anorFogDoor = new FogDoor();
		anorLondo.at(45, 0).setGround(anorFogDoor);
		anorFogDoor.addDestination("Profane Capital", profaneCapital.at(38, 25));

		profaneCapital.at(37,11).addActor(new Vendor());

//		 Place Yhorm the Giant/boss in the map
		YhormTheGiant Yhorm = new YhormTheGiant();
		profaneCapital.at(6, 25).addActor(Yhorm);
		Yhorm.setInitialPosition(profaneCapital.at(6, 25));

		StormRuler stormRuler = new StormRuler();
		profaneCapital.at(7,25).addItem(stormRuler);

		Skeleton skeleton = new Skeleton();
		profaneCapital.at(60,20).addActor(skeleton);
		skeleton.setInitialPosition(profaneCapital.at(60,20));

		Skeleton skeleton2 = new Skeleton();
		profaneCapital.at(20,1).addActor(skeleton2);
		skeleton2.setInitialPosition(profaneCapital.at(20,1));

		Skeleton skeleton3 = new Skeleton();
		profaneCapital.at(35,2).addActor(skeleton3);
		skeleton3.setInitialPosition(profaneCapital.at(35,2));

		Skeleton skeleton4 = new Skeleton();
		profaneCapital.at(22,12).addActor(skeleton4);
		skeleton4.setInitialPosition(profaneCapital.at(22,12));

		Skeleton skeleton5 = new Skeleton();
		profaneCapital.at(30,17).addActor(skeleton5);
		skeleton5.setInitialPosition(profaneCapital.at(30,17));

		AldrichTheDevourer aldrichTheDevourer = new AldrichTheDevourer();
		anorLondo.at(38, 14).addActor(aldrichTheDevourer);
		aldrichTheDevourer.setInitialPosition(anorLondo.at(38, 14));

		world.run();
	}
}
