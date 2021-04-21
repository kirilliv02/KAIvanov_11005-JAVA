import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bot bot = new Bot();
        try {
            bot.run();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
