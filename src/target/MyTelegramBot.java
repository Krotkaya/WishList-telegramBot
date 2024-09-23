package target;

//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyTelegramBot {

    public String getBotUsername() {
        return "WishList";
    }

    public String getBotToken() {
        return "token"; // Укажите токен вашего бота
    }

//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
//            SendMessage message = new SendMessage();
//            message.setChatId(String.valueOf(update.getMessage().getChatId()));
//            message.setText("Привет! Ты сказал: " + messageText);
//
//            try {
//                execute(message); // Отправляем ответное сообщение
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void main(String[] args) {
        System.out.println("Здарова!");
//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new MyTelegramBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }
}
