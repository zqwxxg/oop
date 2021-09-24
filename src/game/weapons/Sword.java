package game.weapons;

import java.util.Random;

public class Sword extends MeleeWeapon {

    protected Random rand = new Random();

    public Sword(String name, char displayChar, int damage, String verb, int hitRate, int price){
        super(name, displayChar, damage, verb, hitRate, price);
    }

    @Override
    public int damage() {
        if (rand.nextInt(100)<= 20){
            verb = "critically strikes";
            return damage *= 2;
        }
        return damage;
    }
}
