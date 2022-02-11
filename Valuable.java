//Jennifer McCarthy, 930124-0983

abstract public class Valuable {

    protected String name;

    public Valuable (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public double getValue();

    public double getTotal() {
        return getValue() + getValue() / 4;
    }

}