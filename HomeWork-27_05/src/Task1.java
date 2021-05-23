import java.util.Scanner;

public class Task1 {
    static long factorial = 1;
    static int x;

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        x = sc.nextInt();

        Thread thread1 = new Thread(() -> {
            int min = Math.min(x, 5);
            for (int i = 1; i <= min; i++) {
                factorial *= i;
                System.out.println("Факториал " + i + " = " + factorial);
            }
        });

        Thread thread2 = new Thread(() -> {
            int min = Math.min(x, 10);
            for (int i = 6; i <= min; i++) {
                factorial *= i;
                System.out.println("Факториал " + i + " = " + factorial);
            }
        });
        Thread thread3 = new Thread(() -> {
            int min = Math.min(x, 15);
            for (int i = 11; i <= min; i++) {
                factorial *= i;
                System.out.println("Факториал " + i + " = " + factorial);
            }
        });
        Thread thread4 = new Thread(() -> {
            int min = Math.min(x, 20);
            for (int i = 16; i <= min; i++) {
                factorial *= i;
                System.out.println("Факториал " + i + " = " + factorial);
            }
        });
        if (x < 1 || x > 20) {
            System.out.println("Неверное число");
        } else if (x <= 5) {
            thread1.start();
            thread1.join();
        } else if (x <= 10) {
            thread1.start();
            thread1.join();
            Thread.sleep(1500);

            thread2.start();
            thread2.join();
        } else if (x <= 15) {
            thread1.start();
            thread1.join();
            Thread.sleep(1500);

            thread2.start();
            thread2.join();
            Thread.sleep(1500);

            thread3.start();
            thread3.join();
        } else {
            thread1.start();
            thread1.join();
            Thread.sleep(1500);

            thread2.start();
            thread2.join();
            Thread.sleep(1500);

            thread3.start();
            thread3.join();
            Thread.sleep(1500);

            thread4.start();
            thread4.join();
        }
    }


}
