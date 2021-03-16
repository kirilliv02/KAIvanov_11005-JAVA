import java.util.*;

public class Task2 {
    Map<String, Map<String, Integer>> map = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new Task2().menu();
    }

    void menu() {
        System.out.println("[1] Добавить");
        System.out.println("[2] Посмотреть");
        System.out.println("[3] Выход");
        char key = sc.next().charAt(0);
        switch (key) {
            case '1':
                addCustomer();
                menu();
                break;
            case '2':
                checkCustomers();
                menu();
                break;
            case '3':
                break;
            default:
                menu();
                break;
        }
    }

    void addCustomer() {
        System.out.print("Введите строку в формате(Пользователь, товар, кол-во): ");
        String[] str = new String[3];
        str[0] = sc.next().replaceAll("[,]", "");
        str[1] = sc.next().replaceAll("[,]", "");
        str[2] = sc.next().replaceAll("[,]", "");
        if (str.length == 3) {
            Map<String, Integer> mp = new HashMap<>();
            if (map.containsKey(str[0])) {
                mp = map.get(str[0]);
            }

            mp.put(str[1], Integer.parseInt(str[2]));
            map.put(str[0], mp);
        } else {
            System.out.println("Строка введена неверно!");
            addCustomer();
            return;
        }
    }

    void checkCustomers() {
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Map.Entry<String, Integer> entry1 : entry.getValue().entrySet())
                System.out.println("\t" + entry1.getKey() + " - " + entry1.getValue());
        }
    }
}
