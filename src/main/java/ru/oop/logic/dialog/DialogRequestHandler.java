package ru.oop.logic.dialog;

import ru.oop.logic.commands.Command;
import ru.oop.logic.RequestHandler;
import ru.oop.logic.Request;
import ru.oop.logic.OutputWriter;
import ru.oop.logic.Response;

import java.util.regex.Matcher;
import java.util.List;

public class DialogRequestHandler implements RequestHandler {
    private final List<Command> commands;

    public DialogRequestHandler(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void handle(Request request, OutputWriter writer) {
        Response response = null;

        // Сначала извлекаем сообщение и команды
        String message = request.getMessage();

        for (Command command : commands) {
            Matcher matcher = command.getCommandPattern().matcher(message);
            if (matcher.matches()) {
                // Если нашло совпадение, обновим response
                response = command.executeCommand(request, matcher, null);
                break; // Прекращаем цикл, если команда найдена
            }
        }

        if (response == null) {
            response = new Response("Неизвестная команда.");
        }

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