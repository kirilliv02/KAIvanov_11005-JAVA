import java.util.Comparator;

public class BuyerByCityCountComparator implements Comparator<Buyer> {
    @Override
    public int compare(Buyer o1, Buyer o2) {
        return Integer.compare(o1.cities.size(), o2.cities.size());
    }
}
