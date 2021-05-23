import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {
    static ArrayList<String> words = new ArrayList<>();
    static ArrayList<String> evenWords = new ArrayList<>();
    static ArrayList<String> unEvenWords = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        File file = new File("./input.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNext()){
            String word = sc.next().toLowerCase();
            if (!checkWord(word)){
                words.add(word.toLowerCase());
            }
        }

        Thread thread1 = new Thread(()->{
            for (String word : words) {
                if (word.length() % 2 == 1) {
                    System.out.println("Поток 1, слово " + word);
                    unEvenWords.add(word);
                }
            }
        });

        Thread thread2 = new Thread(()->{
            for (String word : words) {
                if (word.length() % 2 == 0) {
                    System.out.println("Поток 2, слово " + word);
                    evenWords.add(word);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(words);
        System.out.println(unEvenWords);
        System.out.println(evenWords);
    }

    static boolean checkWord(String word){
        for (String s : words) {
            if (s.equals(word)) {
                return true;
            }
        }
        return false;
    }
}
