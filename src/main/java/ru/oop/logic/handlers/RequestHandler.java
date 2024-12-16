package ru.oop.logic.handlers;

import ru.oop.logic.OutputWriter;
import ru.oop.logic.Request;
import ru.oop.logic.commands.Command;
import ru.oop.logic.models.User;

public interface RequestHandler {
    void setCommands(Command[] commands);
    void handle(Request request, OutputWriter writer, User user);
}