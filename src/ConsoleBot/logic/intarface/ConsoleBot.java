package ConsoleBot.logic.intarface;

import ConsoleBot.logic.Request;
import ConsoleBot.logic.OutputWriter;
import ConsoleBot.logic.RequestHandler;

public class ConsoleBot implements Bot {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final RequestHandler requestHandler;

    public ConsoleBot(InputReader inputReader, OutputWriter outputWriter, RequestHandler requestHandler) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.requestHandler = requestHandler;
    }

    @Override
    public void startBot() {
        System.out.println("Добро пожаловать в консольного бота! Введите ваше сообщение.");
        while (true) {
            Request request = inputReader.read();
            requestHandler.handle(request, outputWriter);
        }
    }
}