import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Main {
    static ArrayList<Buyer> buyers = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File("orders.txt")));

        String line = reader.readLine();
        while (line != null) {
            String[] str = line.split("[|]");
            addToArray(str);
            line = reader.readLine();
        }

        BuyerByNameComparator buyerByNameComparator = new BuyerByNameComparator();
        BuyerByCityCountComparator buyerByCityCountComparator = new BuyerByCityCountComparator();
        BuyerByOrdersCountComparator buyerByOrdersCountComparator = new BuyerByOrdersCountComparator();

        TreeSet<Buyer> sortedByName = new TreeSet<>(buyerByNameComparator.thenComparing(buyerByCityCountComparator.thenComparing(buyerByOrdersCountComparator)));
        TreeSet<Buyer> sortedByOrders = new TreeSet<>(buyerByOrdersCountComparator.thenComparing(buyerByCityCountComparator.thenComparing(buyerByNameComparator)));

        sortedByName.addAll(buyers);
        sortedByOrders.addAll(buyers);

        writeProductsToFile(sortedByName, "sortedByName.txt");
        writeProductsToFile(sortedByOrders, "sortedByOrders.txt");


        printAll(buyers);


    }

    static void addToArray(String[] str) {
        String buyerName = str[0];
        String cityName = str[1];
        String orderName = str[2];
        int count = Integer.parseInt(str[3]);

        Order order = new Order(orderName, count);
        City city = new City(cityName, order);
        Buyer buyer = new Buyer(buyerName, city);

        if (buyers.size() == 0) {
            buyers.add(buyer);
        } else {
            for (int i = 0; i < buyers.size(); i++) {
                if (checkNameBuyer(buyerName)) {
                    int idCity = checkNameCity(buyers.get(i).cities, cityName);
                    if (idCity != -1) {
                        int idOrder = checkNameOrder(buyers.get(i).cities.get(idCity).orders, orderName);
                        if (idOrder != -1) {
                            buyers.get(i).cities.get(idCity).orders.get(idOrder).count += count;
                        } else {
                            buyers.get(i).cities.get(idCity).orders.add(order);
                        }
                    } else {
                        buyers.get(i).cities.add(city);
                    }
                } else {
                    buyers.add(buyer);
                }
            }
        }
    }

    static boolean checkNameBuyer(String name) {
        for (Buyer buyer : buyers) {
            if (buyer.name.equals(name)) return true;
        }
        return false;
    }

    static int checkNameCity(ArrayList<City> cities, String name) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).name.equals(name)) return i;
        }
        return -1;
    }

    static int checkNameOrder(ArrayList<Order> orders, String name) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).name.equals(name)) return i;
        }
        return -1;
    }

    public static void writeProductsToFile(TreeSet<Buyer> buyers, String name) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(buyers);
        } catch (IOException ignored) {
        }
    }

    static void printAll(ArrayList<Buyer> list) {
        for (Buyer buyer : list) {
            System.out.println(buyer.name + ":");
            for (City city : buyer.cities) {
                System.out.println("\t" + city.name + ":");
                for (Order order : city.orders) {
                    System.out.println("\t\t" + order.toString());
                }
            }
        }
    }


}
