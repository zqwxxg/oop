package game;

public class Broadsword extends Sword{
    private double price;
    private AttackAction attackAction;

    private static final String BROADSWORD_NAME = "Broadsword";
    private static final char BROADSWORD_CHAR = '/';
    private static final int BROADSWORD_DAMAGE = 30;
    private static final String BROADSWORD_VERB = "slash";
    private static final int BROADSWORD_HIT_RATE = 80;
    private static final double BROADSWORD_PRICE = 500;

    public Broadsword(){
        super(BROADSWORD_NAME, BROADSWORD_CHAR, BROADSWORD_DAMAGE, BROADSWORD_VERB, BROADSWORD_HIT_RATE);
        this.price = BROADSWORD_PRICE;
    }

}
