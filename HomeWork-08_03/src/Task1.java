import java.util.Scanner;
import java.util.Stack;

public class Task1 {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        char [] array1 = {'(', '[', '{'};
        char [] array2 = {')', ']', '}'};

        boolean check = true;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < array1.length; j++) {
                if (str.charAt(i)==array1[j]){
                    stack.push(str.charAt(i));
                }
                if (str.charAt(i)==array2[j]){
                    if (stack.peek()==array1[j]){
                        stack.pop();
                    } else {
                        check = false;
                        break;
                    }
                }
            }
            if (!check){
                System.out.println("false");
                break;
            }
        }
        if (check){
            System.out.println("true");
        }
    }
}
