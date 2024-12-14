package ru.oop.logic.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private static final Map<String, Command> commands = new HashMap<>();

    // Метод для регистрации команды
    public static void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    // Получение команды по имени
    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    // Проверка наличия команды
    public static boolean hasCommand(String commandName) {
        return commands.containsKey(commandName);
    }
}