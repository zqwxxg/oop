package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.items.CindersOfaLord;

/**
 * The boss of Design o' Souls
 */
public abstract class LordOfCinder extends Enemies {
    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints, int soulCount) {
        super(name, displayChar, hitPoints, soulCount);
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public void resetInstance(GameMap map, Status status, String direction) {
        if (status == Status.ENEMIES_KILLED) {
//            Location location = map.locationOf(this);
//            int x = location.x();
//            int y = location.y();
//            switch (direction) {
//                // bonfire is at north of the location that player rested on
//                case "North" -> y -= 1;
//                // bonfire is at north-east of the location that player rested on
//                case "North-East" -> {
//                    x += 1;
//                    y -= 1;
//                }
//                // bonfire is at east of the location that player rested on
//                case "East" -> x += 1;
//                // bonfire is at south-east of the location that player rested on
//                case "South-East" -> {
//                    x += 1;
//                    y += 1;
//                }
//                // bonfire is at south of the location that player rested on
//                case "South" -> y += 1;
//                // bonfire is at south-west of the location that player rested on
//                case "South-West" -> {
//                    x -= 1;
//                    y += 1;
//                }
//                // bonfire is at west of the location that player rested on
//                case "West" -> x += 1;
//                // bonfire is at north-west of the location that player rested on
//                case "North-West" -> {
//                    x -= 1;
//                    y -= 1;
//                }
//            }
//            location = map.at(x, y);
            map.locationOf(this).addItem(new CindersOfaLord(map));
            map.removeActor(this);
        } else {
            hitPoints = maxHitPoints;
            map.moveActor(this, initialPosition);
            // remove follow behaviour from behaviours list
            behaviours.removeIf(behaviour -> behaviour instanceof FollowBehaviour);
            followBehaviourAdded = false;
        }
    }
}

