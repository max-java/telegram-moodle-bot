package by.jrr.telegrammoodlebot.bot.service;//package by.jrr.telegram.bot.service;

import by.jrr.telegrammoodlebot.bot.JavaQuestionBot;
import by.jrr.telegrammoodlebot.model.TgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessageService {

    @Autowired
    JavaQuestionBot javaQuestionBot;


    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            javaQuestionBot.execute(sendMessage.setReplyMarkup(Keyboards.getMenu()));
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(text);
        try {
            javaQuestionBot.execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(TgUser tgUser, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(tgUser.getId().toString());
        sendMessage.setText(text);
        try {
            javaQuestionBot.execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendHtmlMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
//        sendMessage.setParseMode("HTML");
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(text);
        try {
            javaQuestionBot.execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
