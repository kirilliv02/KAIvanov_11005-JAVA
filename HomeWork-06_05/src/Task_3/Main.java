package Task_3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);
    static Storage storage = new Storage();

    public static void main(String[] args) {
        storage.addListener(() -> storage.readProducts().forEach(System.out::println));
        storage.addListener(() -> {
            ArrayList<Product> products = storage.readProducts();
            int sum = 0;
            for (Product product : products) {
                sum += product.count;
            }
            System.out.println("Общее занятое место на складе: " + sum);
        });
        storage.addListener(() -> {
            ArrayList<Product> products = storage.readProducts();
            Product maxProduct = products.get(0);
            for (Product product : products) {
                if (product.count > maxProduct.count)
                    maxProduct = product;
            }
            System.out.println("Товара с наибольшим количеством: " + maxProduct.name);
        });
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
                storage.checkProducts();
                menu();
                break;
            case '2':
                storage.addProduct();
                menu();
                break;
            case '3':
                storage.removeProduct();
                menu();
                break;
            case '4':
                storage.changeProduct();
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


}
