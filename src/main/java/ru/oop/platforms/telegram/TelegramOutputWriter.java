package ru.oop.platforms.telegram;

import ru.oop.logic.other.OutputWriter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramOutputWriter implements OutputWriter {
    private final String chatId;
    private final TelegramBot bot;

    public TelegramOutputWriter(String chatId, TelegramBot bot) {
        this.chatId = chatId;
        this.bot = bot;
    }

    @Override
    public void write(String response) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(response)
                .build();

        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
//собрать объект с помощью builder и execute. Базу данных не подключаем пока, эмулируем процесс работы с ней. Реализовать диалог тг бота. Чтобы для всех сущностей кот хран в бд появился отдельный класс. Поля дб логически объединены. Сущности: вишлист кот хранит в кач поля список заказов и bot.execute(SendMessage.builder().);
// Надо спрашивать информацию пользователя. Пользователь присылает сообщение: список в кот ъочет добавить/по командам сначала куда потом что и тд. Если одной: j присыл сообщ, мы разбираем команды, потом присылаем в сервис обработки команды. Список и пожелание например. Их заворачиваем в объекты и отправляем их в дао. Спиисок вишей лучше в отдельный список. Поле: список классов вишей. ЧТоб потом при переносе в базу класссы можно было преобр в табл базы с пом кибернет или тип того. Надо новый handler кот разбирает что пол написал, потом сервис кот разбирает что пол написал, потом дао кот раб со списками.
// База живет отдельно. ORM позвол отобр данные из табл базы в конкрет класс объекта. Для каждого класса в табл дб класс dao. Кибренейт сначала создаст табл в базе. В классе dao убираем логику работы и заменяем ее на логику кибернейт. Для этого и надо разделееие и выделение dao в отдельный слой. Надо дао для вишей отдельных и для списков этих пожеланий. Все что сохранение удаления это дао для вишей, что кас списков (отдел именованные списки кот хран подмн-во вишей) для этого вторая дао.