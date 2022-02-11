//Jennifer McCarthy, 930124-0983

public class Stock extends Valuable {

    private int amount;
    private double price;

    public Stock (String name, int amount, double price) {
        super(name);
        this.amount = amount;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public double getValue() {
        return amount * price;
    }

    public void setStock (int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s, värde: %.2f, antal: %d, kurs: %.1f", name, getTotal(), amount, price);
    }
}
