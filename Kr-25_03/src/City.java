import java.util.ArrayList;

public class City {
    String name;
    ArrayList<Order> orders = new ArrayList<>();

    public City(String name, Order order) {
        this.name = name;
        orders.add(order);
    }
}
