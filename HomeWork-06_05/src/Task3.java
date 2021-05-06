import java.util.ArrayList;
import java.util.Scanner;

public class Task3 {
    Scanner sc = new Scanner(System.in);
    Storage storage = new Storage();

    public static void main(String[] args) {
        new Task3().menu();
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
                storage.addListener(System.out::println);
                menu();
                break;
            case '3':
                storage.removeProduct();
                storage.addListener(System.out::println);
                menu();
                break;
            case '4':
                storage.changeProduct();
                storage.addListener(System.out::println);
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
