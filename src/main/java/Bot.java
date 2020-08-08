import com.truedev.kinoposk.api.model.film.Data;
import com.truedev.kinoposk.api.model.film.FilmExt;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Bot extends TelegramLongPollingBot {
    public static void main (String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(new Bot());
    }

    public void sendMsg(Message message, String text, Data posterUrl) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);

       /* SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId());
        sendPhoto.setPhoto(posterUrl);
        execute(sendPhoto); */
        execute(sendMessage);
    }

    public void sendMsg(Message message, String text, boolean reply) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);

        if (reply) {
            sendMessage.setReplyToMessageId(message.getMessageId());
        }
        execute(sendMessage);

    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        FilmExt movie = Kinopoisk.generateMovie();

        if (message != null && message.hasText()) {
            if (message.getText().equals("/start")) {
                try {
                    sendMsg(message, "Привет \uD83D\uDC4B, введи любую букву, что бы бот выдал тебе рандомный фильм \uD83D\uDE09 \n\n" +
                            "Команда /bug выведет баги, которые замечены на данный момент и над которыми уже работают\n\n" +
                            "По команда /dev вы можете узнать, над чем сейчас работает разработчик" , true);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (message.getText().equals("/bug")) {
                try {
                    sendMsg(message, "На данный момент, бот находится на стадии разработки, поэтому могут быть какие - либо баги \uD83D\uDE0C \n\n" +
                            "Самый частый баг, на данный момент, это невозможность быстро получать второй фильм \uD83D\uDE15 \n\n" +
                            "Иногда приходится писать 2 - 3 раза, что бы бот отрегировал и выдал второй рандомный фильм \uD83D\uDE21 \n\n", true);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (message.getText().equals("/dev")) {
                try {
                    sendMsg(message, "Сейчас идет работа, над фиксом багов и над более красивым выводом фильмов \uD83D\uDE0F \n ",true);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (message.getText().equals("Film")) {
                try {
                    {
                        sendMsg(message, Kinopoisk.formDescription(movie), movie.getData());
                    }
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }


    }

   /* private static File getFileFromUrl(String link) throws IOException {
        URL url = new URL(link);
        BufferedImage img = ImageIO.read(url);
        File file = new File("image.jpg");
        ImageIO.write(img, "jpg", file);
        return file;
    } */

    public String getBotUsername() {
        return "YourBotName";
    }

    public String getBotToken() {
        return "YourBotToken";
    }


}

// @RandFilmbot :D


