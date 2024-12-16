package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.models.User; // Предполагаем, что у вас есть модель User

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    Pattern getCommandPattern();
    Response executeCommand(Request request, Matcher matched, User currentUser);
}
