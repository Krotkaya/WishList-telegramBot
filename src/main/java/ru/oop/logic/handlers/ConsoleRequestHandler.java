package ru.oop.logic.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.oop.logic.OutputWriter;
import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.commands.Command;
import ru.oop.logic.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleRequestHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleRequestHandler.class); // Создаем логгер
    private Command[] commands;

    @Override
    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void handle(Request request, OutputWriter writer, User user) {
        Response response = null;


        String message = request.getMessage();
        logger.debug("Received message: {}", message);

        logger.info("Total registered commands: {}", commands.length);

        for (Command command : commands) {
            logger.debug("Checking command: {}", command.getClass().getSimpleName());
            logger.debug("Command pattern: {}", command.getCommandPattern());

            Pattern pattern = command.getCommandPattern();
            Matcher matcher = pattern.matcher(message);

            if (matcher.matches()) {
                logger.info("Command matched: {}", command.getCommandPattern());
                try {
                    response = command.executeCommand(request, matcher, user);
                    logger.info("Command executed successfully: {}", command.getClass().getSimpleName());
                } catch (Exception e) {
                    logger.error("Error while executing command: {}", command.getClass().getSimpleName(), e);
                    response = new Response("Ошибка выполнения команды.");
                }
                break;
            } else {
                logger.debug("No match for command: {}", command.getClass().getSimpleName());
            }
        }


        if (response == null) {
            logger.warn("Unknown command: {}", message);
            response = new Response("Неизвестная команда.");
        }

        logger.debug("Sending response: {}", response.getMessage());
        writer.write(response.getMessage());
        writer.write(String.valueOf(response));
    }
}
