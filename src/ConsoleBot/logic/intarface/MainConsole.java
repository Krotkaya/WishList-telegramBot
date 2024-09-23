package ConsoleBot.logic.intarface;

import ConsoleBot.logic.ConsoleRequestHandler;

public class MainConsole {
    public static void main(String[] args) {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();
        ConsoleRequestHandler requestHandler = new ConsoleRequestHandler();

        ConsoleBot bot = new ConsoleBot(inputReader, outputWriter, requestHandler);
        bot.startBot();
    }
}