import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //Task 1
        System.out.println("Task 1");
        String[] strings = {
                "asasfassd",
                "rgwrheh",
                "agg",
                "bfb",
                "art4",
                "fdb"};
        Arrays.stream(strings)
                .filter(p -> p.charAt(0) == 'a')
                .sorted()
                .forEach(x -> System.out.print(x + " "));

        //Task 2
        System.out.println("\nTask 2");
        int[] numbers = {11, 848, 584, 541841, 4848, 18, 8455, 885, 47, 11, 99, 18};
        Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.comparingInt(x -> x % 10))
                .map(x -> x / 10)
                .distinct()
                .forEach(x -> System.out.print(x + " "));

        //Task 3
        System.out.println("\nTask 3");
        Path path = Paths.get("./orders.txt");
        Map<String, Integer> map = new HashMap<>();

        try (Stream<String> lineStream = Files.lines(path)) {

            lineStream
                    .map(s -> s.split("[|]"))
                    .forEach(x -> {
                        if (map.containsKey(x[0])) {
                            map.put(x[0], Integer.valueOf(x[1]) + map.get(x[0]));
                        } else {
                            map.put(x[0], Integer.valueOf(x[1]));
                        }
                    });

        } catch (IOException ignored) {
        }

        map.forEach((k, v) -> System.out.println(k + " " + v));


    }
}
