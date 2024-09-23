package ConsoleBot.logic.intarface;

import ConsoleBot.logic.Response;
import ConsoleBot.logic.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter {//отвечает за вывод ответов в консоль
    @Override
    public void write(Response response) {
        System.out.println("Ответ: " + response.getMessage());
    }
}