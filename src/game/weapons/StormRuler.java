package game.weapons;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.weapons.activeActions.Charge;
import game.weapons.activeActions.WindSlash;

import java.util.ArrayList;
import java.util.List;

public class StormRuler extends Sword{
    private ArrayList<WeaponAction> stormRulerActiveActions = new ArrayList<>();

    public StormRuler(){
        super("Storm Ruler", '7', 70, "slash", 60, 2000);
        Charge charge = new Charge(this);
        WindSlash windSlash = new WindSlash(this);
        stormRulerActiveActions.add(charge);
        stormRulerActiveActions.add(windSlash);
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return stormRulerActiveActions.get(0);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = super.getAllowableActions();
        Charge charge = new Charge(this);
        actions.add(charge);
        if(charge.getIsFullyCharge()){
            actions.remove(charge);
            actions.add(new WindSlash(this));
        }
        return actions;
    }
}
