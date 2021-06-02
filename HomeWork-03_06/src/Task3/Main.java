package Task3;

import java.util.Scanner;
import java.util.concurrent.*;

import static java.lang.StrictMath.sqrt;

public class Main {

    static class MyRunnable implements Runnable {

        int a;
        int b;

        MyRunnable(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }


        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());

            for (int p = a; p <= b; p++) {
                boolean prime = true;
                for (int i = 2; i <= sqrt(p); i++)
                    if (p % i == 0) prime = false;
                if (prime) System.out.println(p);
            }


        }

    }

    public static void main(String[] args) throws InterruptedException {


        ExecutorService service = Executors.newFixedThreadPool(4);



        Scanner sc = new Scanner(System.in);

        int b = sc.nextInt();

        int p = 0;
        int a = 1;
        while (p!=b){
            if (p+10>b){
                p =b;
            } else {
                p = a+9;
            }
            service.submit(new MyRunnable(a,p));
            a+=10;
            Thread.sleep(1500);

        }

        service.shutdown();



    }


}
