package ru.oop.logic.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.oop.logic.OutputWriter;
import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.commands.Command;
import ru.oop.logic.models.User;
import ru.oop.platforms.telegram.TelegramOutputWriter;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TelegramRequestHandler implements RequestHandler {
    private Command[] commands;
    private static final Logger LOG = LoggerFactory.getLogger(TelegramRequestHandler.class); // Для логирования

    @Override
    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void handle(Request request, OutputWriter writer, User user) {
        Response response = null;

        // Извлекаем сообщение из запроса
        String message = request.getMessage();
        LOG.debug("Received message: {}", message); // Логируем получение сообщения

        // Логируем количество зарегистрированных команд
        LOG.debug("Total registered commands: {}", commands.length);

        // Перебираем все зарегистрированные команды
        for (Command command : commands) {
            LOG.debug("Checking command: {}", command.getClass().getSimpleName()); // Логируем имя текущей команды
            LOG.debug("Command pattern: {}", command.getCommandPattern()); // Логируем шаблон команды

            // Должны возвращать не строку, а pattern
            Pattern pattern = command.getCommandPattern();
            Matcher matcher = pattern.matcher(message);

            // Проверяем, соответствует ли сообщение текущей команде
            if (matcher.matches()) {
                LOG.debug("Command matched: {}", command.getCommandPattern()); // Логируем успешное совпадение
                try {
                    response = command.executeCommand(request, matcher, user); // Выполнение команды
                    LOG.info("Command executed successfully: {}", command.getClass().getSimpleName()); // Логируем успешное выполнение
                } catch (Exception e) {
                    LOG.error("Error while executing command: {}", command.getClass().getSimpleName(), e); // Логируем ошибку при выполнении команды
                    response = new Response("Ошибка выполнения команды."); // error
                }
                break; // Прекращаем перебор, так как команда найдена
            } else {
                LOG.debug("No match for command: {}", command.getClass().getSimpleName()); // Логируем, если команда не совпала
            }
        }

        // Если команда не найдена, возвращаем сообщение об ошибке
        if (response == null) {
            LOG.warn("Unknown command: {}", message); // Логируем неизвестную команду
            response = new Response("Неизвестная команда.");
        }

        // Пишем ответ через OutputWriter
        LOG.debug("Sending response: {}", response.getMessage());
        writer.write(response.getMessage());
    }
}
