//Jennifer McCarthy, 930124-0983

public class Jewelry extends Valuable{

    private int gemstones;
    private boolean gold;

    public Jewelry (String name, int gemstones, boolean gold) {
        super(name);
        this.gemstones = gemstones;
        this.gold = gold;
    }

    public int getGemstones() {
        return gemstones;
    }

    public boolean isGold() {
        return gold;
    }

    @Override
    public double getValue() {
        if (gold) {return 2000 + gemstones * 500;}
        else {return 700 + gemstones * 500;}
    }

    @Override
    public String toString() {
        if (gold) {
            return String.format("%s, värde: %.2f, av: guld, antal ädelstenar: %d", name, getTotal(), gemstones);
        } else {
            return String.format("%s, värde: %.2f, av: silver, antal ädelstenar: %d", name, getTotal(), gemstones);
        }
    }

}
