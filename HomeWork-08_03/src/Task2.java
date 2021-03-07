import java.util.Scanner;
import java.util.Stack;

public class Task2 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        String[] array = str.split(" ");

        for (int i = 0; i < array.length; i++) {
            if (tryParseInt(array[i])) stack.push(Integer.parseInt(array[i]));
            else {
                int
                        b = stack.pop();
                int a = stack.pop();

                if (array[i].equals("+")) {
                    stack.push(a + b);
                }
                if (array[i].equals("-")) {
                    stack.push(a - b);
                }
                if (array[i].equals("*")) {
                    stack.push(a * b);
                }
                if (array[i].equals("/")) {
                    stack.push(a / b);
                }
            }
        }
        if (stack != null) {
            System.out.println(stack.pop());
        } else {
            System.out.println("false");
        }

    }

    static boolean tryParseInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
