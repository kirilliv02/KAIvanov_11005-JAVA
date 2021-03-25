public class Order {
    String name;
    int count;

    public Order(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return name + " - " + count;
    }
}
