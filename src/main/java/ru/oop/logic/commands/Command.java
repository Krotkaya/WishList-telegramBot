package ru.oop.logic.commands;

import ru.oop.logic.other.Request;
import ru.oop.logic.other.Response;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.models.Wish;
import ru.oop.logic.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    Pattern getCommandPattern();
    Response executeCommand(Request request, Matcher matched, User currentUser);
}
