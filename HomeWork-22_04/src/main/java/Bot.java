import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Bot extends TelegramLongPollingBot {
    private static final String PORT = System.getenv("PORT");
    ArrayList<Tables> tables1 = new ArrayList<>();
    private static int cnt = 0;
    Scanner in = new Scanner(System.in);

    Main main = new Main();

    BotCommand botCommand = new BotCommand();

    private HashMap<String, Method> commands;

    public Bot() {
        commands = new HashMap<>();

        Class botCommand = new BotCommand().getClass();

        for (Method m : botCommand.getDeclaredMethods()) {
            if (!m.isAnnotationPresent(SomeAnnotation.class))
                continue;

            SomeAnnotation cmd = m.getAnnotation(SomeAnnotation.class);
            for (String name : cmd.names())
                commands.put(name, m);
        }
    }

    @Override
    public String getBotUsername() {
        return "Bot";
    }

    @Override
    public String getBotToken() {
        return "1188880883:AAFOMGC65y6OcLHxMrsMq0stpSZ6Vmsg6ok";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage())
            return;

        Message message = update.getMessage();
        String chatId = String.valueOf(message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        String text = message.getText();

        String[] splitted = text.split(" ");
        String command = splitted[0].toLowerCase();
        String[] args = Arrays.copyOfRange(splitted, 1, splitted.length);

        Method m = commands.get(command);
        if (m != null) {
            try {
                sendMessage.setText((String) m.invoke(botCommand, (Object) args));
            } catch (Exception e) {
                sendMessage.setText("Something went wrong, try again");
            }
        } else {
            sendMessage.setText("I don't know this command");
        }
        if (text.equals("/booktable") || cnt == 1) {

            sendMessage.setText(botCommand.booking(cnt, message));
            cnt++;
            if (cnt == 2) {
                cnt = 0;
            }
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void run() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new Bot());

        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT))) {
            while (true) {
                serverSocket.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


