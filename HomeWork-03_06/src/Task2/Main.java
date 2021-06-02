package Task2;


import java.security.InvalidParameterException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {


    public static void main(String[] args) throws InterruptedException, ExecutionException {



        StringBuilder stringBuilder = new StringBuilder();

        Scanner sc = new Scanner(System.in);


        Callable<String> callable = () ->{

            char[] sAlphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

            StringBuilder str = new StringBuilder();
            int size = new Random().nextInt(6);


            for (int i = 0; i < size; i++) {
                int index = new Random().nextInt(sAlphabet.length);
                char a = sAlphabet[index];
                str.append(a);
            }

            System.out.println(Thread.currentThread().getName() + " has added new char to String!");

            return str.toString();
        };

        System.out.print("Введите число: ");
        int size = sc.nextInt();

        if (size < 0)
            throw new InvalidParameterException("Вы ввели отрицательное число!");
        
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        for (int i = 0; i < size; i++) {
            Future<String> future = service.schedule(callable, 3, TimeUnit.SECONDS);
            stringBuilder.append(future.get());
            System.out.println(stringBuilder.toString());
        }
        service.shutdown();
    }

}
