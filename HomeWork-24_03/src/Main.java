import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().menu();
    }

    void menu() {
        System.out.println("[1] Посмотреть все товары");
        System.out.println("[2] Добавть товар");
        System.out.println("[3] Удалить товар");
        System.out.println("[4] Изменить кол-во товара");
        System.out.println("[5] Выход");

        char key = sc.next().charAt(0);
        switch (key) {
            case '1':
                checkProducts();
                menu();
                break;
            case '2':
                addProduct();
                menu();
                break;
            case '3':
                removeProduct();
                menu();
                break;
            case '4':
                changeProduct();
                menu();
                break;
            case '5':
                break;
            default:
                System.out.println("Неверная команда!");
                menu();
                break;
        }
    }

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
        System.out.print("Введите название товара: ");
        String name = sc.next();
        System.out.print("Введите кол-во товара: ");
        int count = sc.nextInt();
        Product product = new Product(name, count);
        ArrayList<Product> products = readProducts();
        if (products == null) {
            products = new ArrayList<>();
        }
        if (products.size() != 0 && checkProductInList(name)) {
            System.out.println("Данный товар уже существует!");
            return;
        }
        products.add(product);
        writeProductsToFile(products);
    }

    void removeProduct() {
        System.out.print("Введите название товара: ");
        String name = sc.next();
        ArrayList<Product> products = readProducts();
        if (products == null || products.size() == 0) {
            System.out.println("Товаров нет!");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).name.equals(name)) {
                products.remove(i);
                writeProductsToFile(products);
                System.out.println("Продукт удален!");
                return;
            }
        }
        System.out.println("Данный товар не найден!");
    }

    void changeProduct() {
        System.out.print("Введите название товара: ");
        String name = sc.next();
        ArrayList<Product> products = readProducts();
        if (products == null || products.size() == 0) {
            System.out.println("Товаров нет!");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).name.equals(name)) {
                System.out.print("Введите кол-во товара: ");
                products.get(i).count = sc.nextInt();
                writeProductsToFile(products);
                System.out.println("Кол-во товара изменено!");
                return;
            }
        }
        System.out.println("Данный товар не найден!");
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


}
