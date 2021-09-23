package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class Vendor {
    private ArrayList<WeaponItem> weaponsForSale = new ArrayList<>();
    private static final String VENDOR_NAME = "Fire Keeper";

    public void addWeaponsForSale(){
        weaponsForSale.add(new Broadsword());
        weaponsForSale.add(new GiantAxe());
    }

    public String buyWeapon(Actor actor, GameMap map, WeaponItem weapon){
        if(weaponsForSale.contains(weapon)){
            BuyWeaponAction buyWeaponAction = new BuyWeaponAction(weapon);
            return buyWeaponAction.execute(actor, map);
        }
        return "We do not sell this weapon.";
    }
}
