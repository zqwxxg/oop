package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;

import java.util.ArrayList;

public class StormRuler extends Sword{
    private double price;
    private ArrayList<WeaponAction> stormRulerActiveActions = new ArrayList<>();

    private static final String STORM_RULER_NAME = "Storm Ruler";
    private static final char STORM_RULER_CHAR = '7';
    private static final int STORM_RULER_DAMAGE = 70;
    private static final String STORM_RULER_VERB = "slash";
    private static final int STORM_RULER_HIT_RATE = 60;
    private static final double STORM_RULER_PRICE = 2000;

    public StormRuler(){
        super(STORM_RULER_NAME, STORM_RULER_CHAR, STORM_RULER_DAMAGE, STORM_RULER_VERB, STORM_RULER_HIT_RATE);
        this.price = STORM_RULER_PRICE;
    }

    public void addStormRulerActiveAction(StormRuler stormRuler){
        Charge charge = new Charge(stormRuler);
        WindSlash windSlash = new WindSlash(stormRuler, charge);
        stormRulerActiveActions.add(charge);
        stormRulerActiveActions.add(windSlash);
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return stormRulerActiveActions.get(0);
    }
}
