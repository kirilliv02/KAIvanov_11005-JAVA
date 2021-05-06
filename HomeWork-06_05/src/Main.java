import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        //1
        TreeSet<String> treeSet = new TreeSet<>((a,b)->Integer.compare(a.length(), b.length()));
        treeSet.add("ccc");
        treeSet.add("bb");
        treeSet.add("a");
        System.out.println(treeSet);

        //2
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = siftArray(list, x -> x % 2 == 1);
        System.out.println(evenNumbers);



    }

    public static List<Integer> siftArray(List<Integer> list, Predicate<Integer> predicate) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int x : list) {
            if (predicate.test(x))
                result.add(x);
        }
        return result;
    }



}
