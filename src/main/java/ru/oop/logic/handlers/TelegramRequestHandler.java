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
    private static final Logger LOG = LoggerFactory.getLogger(TelegramRequestHandler.class); // Для логирования
    private Command[] commands;

    @Override
    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void handle(Request request, OutputWriter writer, User user) {
        Response response = null;

        String message = request.getMessage();
        LOG.debug("Received message: {}", message);

        LOG.debug("Total registered commands: {}", commands.length);

        for (Command command : commands) {
            LOG.debug("Checking command: {}", command.getClass().getSimpleName());
            LOG.debug("Command pattern: {}", command.getCommandPattern());

            Pattern pattern = command.getCommandPattern();
            Matcher matcher = pattern.matcher(message);

            if (matcher.matches()) {
                LOG.debug("Command matched: {}", command.getCommandPattern());
                try {
                    response = command.executeCommand(request, matcher, user);
                    LOG.info("Command executed successfully: {}", command.getClass().getSimpleName());
                } catch (Exception e) {
                    LOG.error("Error while executing command: {}", command.getClass().getSimpleName(), e);
                    response = new Response("Ошибка выполнения команды.");
                }
                break;
            } else {
                LOG.debug("No match for command: {}", command.getClass().getSimpleName());
            }
        }


        if (response == null) {
            LOG.warn("Unknown command: {}", message);
            response = new Response("Неизвестная команда.");
        }

        LOG.debug("Sending response: {}", response.getMessage());
        writer.write(response.getMessage());
    }
}
