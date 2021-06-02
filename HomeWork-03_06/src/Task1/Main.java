package Task1;

import java.io.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread writer = new Thread(()->{
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./persons.txt"))){
                Person person = RandomPerson.random();
                oos.writeObject(person);
                System.out.println("Writer successfully wrote");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread reader = new Thread(()->{
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./persons.txt"))){
                Person person = (Person) ois.readObject();
                System.out.println(person);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 10; i++) {
            writer.run();
            writer.join();
            reader.run();
            reader.join();
            Thread.sleep(1500);
        }
    }
}
