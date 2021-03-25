import java.io.*;
import java.util.ArrayList;
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
        reader.close();

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
            int idBuyer = checkNameBuyer(buyerName);
            if (idBuyer != -1) {
                int idCity = checkNameCity(buyers.get(idBuyer).cities, cityName);
                if (idCity != -1) {
                    int idOrder = checkNameOrder(buyers.get(idBuyer).cities.get(idCity).orders, orderName);
                    if (idOrder != -1) {
                        buyers.get(idBuyer).cities.get(idCity).orders.get(idOrder).count += count;
                    } else {
                        buyers.get(idBuyer).cities.get(idCity).orders.add(order);
                    }
                } else {
                    buyers.get(idBuyer).cities.add(city);
                }
            } else {
                buyers.add(buyer);
            }
        }
    }

    static int checkNameBuyer(String name) {
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i).name.equals(name)) return i;
        }
        return -1;
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
