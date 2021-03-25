import java.util.ArrayList;

public class Buyer {
    String name;
    ArrayList<City> cities = new ArrayList<>();

    public Buyer(String name, City city) {
        this.name = name;
        cities.add(city);
    }

}
