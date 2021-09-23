package game;

public class GiantAxe extends Axe {
    private double price;
    private AttackAction attackAction;

    private static final String GIANT_AXE_NAME = "Giant Axe";
    private static final char GIANT_AXE_CHAR = 'P';
    private static final int GIANT_AXE_DAMAGE = 50;
    private static final String GIANT_AXE_VERB = "hack";
    private static final int GIANT_AXE_HIT_RATE = 80;
    private static final double GIANT_AXE_PRICE = 1000;

    public GiantAxe(){
        super(GIANT_AXE_NAME, GIANT_AXE_CHAR, GIANT_AXE_DAMAGE, GIANT_AXE_VERB, GIANT_AXE_HIT_RATE);
        this.price = GIANT_AXE_PRICE;
    }
}
