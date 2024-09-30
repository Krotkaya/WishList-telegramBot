package platforms.console;

import platforms.InputReader;
import logic.Request;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Request read() {
        System.out.print("Введите сообщение: ");
        String input = scanner.nextLine();

        return new Request(input);
    }
}