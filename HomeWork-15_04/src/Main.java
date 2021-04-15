import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Bot bot = new Bot();

        while (true) {
            String message = in.nextLine();
            if (message.equals("exit"))
                break;

            String answer = bot.processUserInput(message);
            System.out.println(answer);
        }
    }
}