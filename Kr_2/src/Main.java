import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        switch (x) {
            case 0 -> System.exit(0);
            case 1 -> new Thread(Main::method1).start();
            case 2 -> new Thread(Main::method2).start();
            case 3 -> new Thread(Main::method3).start();
        }
    }

    static void method1() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("./task1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var map = sc != null ? sc.tokens()
                .map(x -> x.split("[|]"))
                .collect(Collectors.groupingBy(x -> x[0]))
                .entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x -> x.getValue().stream().collect(Collectors.groupingBy(y -> y[1]))
                                .entrySet()
                                .stream()
                                .map(n -> n.getKey() + " " + n.getValue().stream()
                                        .map(y -> Integer.parseInt(y[2]))
                                        .reduce(Integer::sum).get().toString())
                                .collect(Collectors.toMap(z -> z.split(" ")[0], z -> Integer.parseInt(z.split(" ")[1])
                                )))) : null;

        map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                x -> x.getValue().entrySet().stream().max(Map.Entry.comparingByValue())
                        .stream().collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue)))).entrySet().forEach(System.out::println);
    }

    static void method2() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("./task2.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var map = sc != null ? sc.tokens().map(x -> x.split("[|]"))
                .collect(Collectors.groupingBy(x -> x[0]))
                .entrySet()
                .stream()
                .map(x -> x.getKey() + " " + x.getValue().stream()
                        .map(y -> Integer.parseInt(y[1]))
                        .reduce(Integer::sum).get().toString())
                .collect(Collectors.toMap(x -> x.split(" ")[0], x -> Integer.parseInt(x.split(" ")[1]))) : null;
        var average = map.values().stream().mapToInt(integer -> integer).average().getAsDouble();
        map.entrySet().stream().filter(x -> x.getValue() > average).forEach(System.out::println);

    }

    public static void method3() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("./task1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var map = sc != null ? sc.tokens()
                .map(x -> x.split("[|]"))
                .collect(Collectors.groupingBy(x -> x[0]))
                .entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x -> x.getValue().stream().collect(Collectors.groupingBy(y -> y[1]))
                                .entrySet()
                                .stream()
                                .map(n -> n.getKey() + " " + n.getValue().stream()
                                        .map(y -> Integer.parseInt(y[2]))
                                        .reduce(Integer::sum).get().toString())
                                .collect(Collectors.toMap(z -> z.split(" ")[0], z -> Integer.parseInt(z.split(" ")[1])
                                )))) : null;
        map.entrySet().forEach(System.out::println);
    }
}
