import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class Bot {
    private HashMap<String, Method> commands;

    public Bot() {
        commands = new HashMap<>();

        for (Method m : this.getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(Command.class))
                continue;

            Command cmd = m.getAnnotation(Command.class);
            for (String name : cmd.aliases())
                commands.put(name, m);
        }
    }
    @Command(aliases = {"привет", "здаров"},
            args = "",
            description = "Будь культурным, поздоровайся.")
    public String hello(String[] args) { return "Привет!!"; }

    @Command(aliases = {"2+2"},
            args = "",
            description = "")
    public String sum(String[] args) { return "4"; }

    @Command(aliases = {"пока"},
            args = "",
            description = "")
    public String bye(String[] args) { return "Возвращайся!"; }

    @Command(aliases = {"какдела"},
            args = "",
            description = "")
    public String howAreYou(String[] args) { return "нормально"; }

    @Command(aliases = {"помощь", "помоги", "команды", "help"},
            args = "",
            description = "Выводит список команд")

    public String help(String[] args) {
        StringBuilder builder = new StringBuilder("Я умею в такие команды:\n");

        for (Method m : this.getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(Command.class))
                continue;

            Command cmd = m.getAnnotation(Command.class);
            builder.append(Arrays.toString(cmd.aliases())).append(": ").append(cmd.description()).append(" - ").append(cmd.args()).append("\n");
        }

        return builder.toString();
    }
    public String processUserInput(String input) {
        if (input.isEmpty())
            return "Я вас не понимаю";

        String[] splitted = input.split(" ");
        String command = splitted[0].toLowerCase();
        String[] args = Arrays.copyOfRange(splitted, 1, splitted.length);

        Method m = commands.get(command);

        if (m == null)
            return "Я вас не понимаю";

        try {
            return (String) m.invoke(this, (Object) args);
        } catch (Exception e) {
            return "Что-то пошло не так, попробуйте ещё раз";
        }
    }


}