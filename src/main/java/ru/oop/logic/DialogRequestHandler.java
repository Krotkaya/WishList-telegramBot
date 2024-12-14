package ru.oop.logic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.oop.logic.commands.Command;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
//подключить библиотеку для логирования, а то тут очень много логов, slf4j
//Существуют уровни логирования, здесь лучше наши логи разбивать по уровням
public class DialogRequestHandler implements RequestHandler {
    private final List<Command> commands;
    private static final Logger LOG = LoggerFactory.getLogger(DialogRequestHandler.class);//для логгирования

    public DialogRequestHandler(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void handle(Request request, OutputWriter writer) {
        Response response = null;

        // Извлекаем сообщение из запроса
        String message = request.getMessage();
        System.out.println("Received message: " + message); // debug
        // Логируем количество зарегистрированных команд
        System.out.println("Total registered commands: " + commands.size());

        // Перебираем все зарегистрированные команды
        for (Command command : commands) {
            System.out.println("Checking command: " + command.getClass().getSimpleName()); // Логируем имя текущей команды
            System.out.println("Command pattern: " + command.getCommandPattern()); // Логируем шаблон команды

            // Должны возвращать не строку, а pattern
            Pattern pattern = Pattern.compile(command.getCommandPattern());
            Matcher matcher = pattern.matcher(message);

            // Проверяем, соответствует ли сообщение текущей команде
            if (matcher.matches()) {
                System.out.println("Command matched: " + command.getCommandPattern()); // Логируем успешное совпадение
                try {
                    response = command.executeCommand(request, matcher, null); // Почему null, так быть не должно, должен быть текущий. Нужно поменять интерйфесе requestHandler
                    System.out.println("Command executed successfully: " + command.getClass().getSimpleName());
                } catch (Exception e) {
                    System.err.println("Error while executing command: " + command.getClass().getSimpleName());
                    e.printStackTrace();
                    response = new Response("Ошибка выполнения команды.");//error
                }
                break; // Прекращаем перебор, так как команда найдена
            } else {
                System.out.println("No match for command: " + command.getClass().getSimpleName());
            }
        }

        // Если команда не найдена, возвращаем сообщение об ошибке
        if (response == null) {
            System.out.println("Unknown command: " + message); // Логируем неизвестную команду
            response = new Response("Неизвестная команда.");
        }

        // Пишем ответ через OutputWriter
        System.out.println("Sending response: " + response.getMessage()); // Логируем отправляемый ответ
        writer.write(response.getMessage());
    }
}
        /*Response response = null;

        for (Command command : commands) {
            response = command.executeCommand(request);
            if (response != null) {
                break;
            }
        }

        if (response == null) {
            response = new Response("Неизвестная команда.");
        }

        writer.write(response);*/