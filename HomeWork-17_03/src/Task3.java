import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Map<String, Integer>> map = new HashMap<>();

        String str = sc.nextLine();
        String [] s = str.split(" ");
        map.put(s[s.length-1], new HashMap<>());
        for (int i = 0; i < s.length-1; i++) {
            Map<String, Integer> mp = new HashMap<>();
            if (map.containsKey(s[i])) {
                mp = map.get(s[i]);
            }
            if (mp.containsKey(s[i+1])) {
                mp.put(s[i+1],mp.get(s[i+1])+1);
            } else {
                mp.put(s[i+1],1);
            }
            map.put(s[i], mp);
        }
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Map.Entry<String, Integer> entry1 : entry.getValue().entrySet())
                System.out.println("\t" + entry1.getKey() + " - " + entry1.getValue());
        }
    }
}
