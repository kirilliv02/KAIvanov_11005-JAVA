import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./text.txt"));

        Map<String, Integer> map = new HashMap();
        while (sc.hasNext()) {
            String str = sc.next().toLowerCase().replaceAll("[,.A-z»♦'<>«@0-9-{}()©!;–&%$\\]\\[#_=+/*<>\"—?:…]", "");
            if (!str.equals("")){
                if (map.containsKey(str)) {
                    map.put(str, map.get(str) + 1);
                } else map.put(str, 1);
            }

        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("---------------\nВсего слов: " + map.size());
    }
}
