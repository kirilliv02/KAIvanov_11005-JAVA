import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./orders.txt"));

        Map<String, Map<String, Map<String, Integer>>> map = new HashMap<>();

        while (sc.hasNext()) {
            String[] str = sc.nextLine().split("[|]");
            if (!map.containsKey(str[0])) {
                Map<String, Integer> map2 = new HashMap<>();
                map2.put(str[2], Integer.parseInt(str[3]));
                Map<String, Map<String, Integer>> map1 = new HashMap<>();
                map1.put(str[1], map2);

                map.put(str[0], map1);
                continue;
            }

            if (!map.get(str[0]).containsKey(str[1])) {
                Map<String, Integer> map2 = new HashMap<>();
                map2.put(str[2], Integer.parseInt(str[3]));
                map.get(str[0]).put(str[1], map2);
                continue;
            }

            if (!map.get(str[0]).get(str[1]).containsKey(str[2])) {
                map.get(str[0]).get(str[1]).put(str[2], Integer.parseInt(str[3]));
                continue;
            }

            map.get(str[0]).get(str[1]).put(str[2], map.get(str[0]).get(str[1]).get(str[2]) + Integer.parseInt(str[3]));
        }

        for (Map.Entry<String, Map<String, Map<String, Integer>>> val : map.entrySet()) {
            System.out.println(val.getKey() + ":");
            for (Map.Entry<String, Map<String, Integer>> val1: val.getValue().entrySet()){
                System.out.println("\t" + val1.getKey() + ":");
                for (Map.Entry<String, Integer> val2: val1.getValue().entrySet())  {
                    System.out.println("\t\t" + val2.getKey() + " - " + val2.getValue());
                }
            }
        }
    }
}
