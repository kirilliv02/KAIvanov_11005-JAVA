import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage{
    ArrayList<ProductsListener> listeners = new ArrayList<>();
    Scanner sc = new Scanner(System.in);


    void checkProducts() {
        ArrayList<Product> products = readProducts();
        if (products == null || products.size() == 0) {
            System.out.println("Нет товаров!");
        } else {
            for (Product product : products) {
                System.out.println(product.toString());
            }
        }
    }

    void addProduct() {
        ArrayList<Product> products = readProducts();
        System.out.print("Введите название товара: ");
        String name = sc.next();
        System.out.print("Введите кол-во товара: ");
        int count = sc.nextInt();
        Product product = new Product(name, count);
        if (products == null) {
            products = new ArrayList<>();
        }
        if (products.size() != 0 && checkProductInList(name)) {
            System.out.println("Данный товар уже существует!");
            return;
        }
        products.add(product);
        writeProductsToFile(products);
        for (ProductsListener listener: listeners){
            listener.productsUpdated();
        }
    }

    void removeProduct() {
        ArrayList<Product> products = readProducts();
        System.out.print("Введите название товара: ");
        String name = sc.next();
        if (products == null || products.size() == 0) {
            System.out.println("Товаров нет!");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).name.equals(name)) {
                products.remove(i);

                System.out.println("Продукт удален!");
                writeProductsToFile(products);
                return;
            }
        }
        System.out.println("Данный товар не найден!");
        for (ProductsListener listener: listeners){
            listener.productsUpdated();
        }
    }

    void changeProduct() {
        ArrayList<Product> products = readProducts();
        System.out.print("Введите название товара: ");
        String name = sc.next();
        if (products == null || products.size() == 0) {
            System.out.println("Товаров нет!");
            return;
        }
        for (Product product : products) {
            if (product.name.equals(name)) {
                System.out.print("Введите кол-во товара: ");
                product.count = sc.nextInt();
                System.out.println("Кол-во товара изменено!");
                writeProductsToFile(products);
                return;
            }
        }
        System.out.println("Данный товар не найден!");
        for (ProductsListener listener: listeners){
            listener.productsUpdated();
        }
    }

    boolean checkProductInList(String name) {
        ArrayList<Product> products = readProducts();
        for (Product product : products) {
            if (product.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void writeProductsToFile(ArrayList<Product> products) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/orders.txt"))) {
            oos.writeObject(products);
        } catch (IOException ignored) {
        }
    }

    public ArrayList<Product> readProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/orders.txt"))) {
            return (ArrayList<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
        return null;
    }

    public void addListener(ProductsListener listener){
        if (!listeners.contains(listener))
            listeners.add(listener);
    }

    public void removeListener(){
        listeners = new ArrayList<>();
    }
}
