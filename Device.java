//Jennifer McCarthy, 930124-0983

public class Device extends Valuable{

    private int price;
    private double condition;

    public Device (String name, int price, double condition) {
        super (name);
        this.price = price;
        this.condition = condition;
    }

    public double getPrice () {
        return price;
    }

    public double getCondition() {
        return condition;
    }

    @Override
    public double getValue() {
        return ((condition / 10) * price);
    }

    @Override
    public String toString() {
        return String.format("%s, värde: %.2f, inköpspris: %d skick: %.1f", name, getTotal(), price, condition);
    }

}
