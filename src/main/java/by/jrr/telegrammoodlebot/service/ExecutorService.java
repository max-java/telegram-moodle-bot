package by.jrr.telegrammoodlebot.service;

import by.jrr.telegrammoodlebot.bot.JavaQuestionBot;
import by.jrr.telegrammoodlebot.model.ServiceMessage;
import by.jrr.telegrammoodlebot.model.UserContacts;
import by.jrr.telegrammoodlebot.util.Convertors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class ExecutorService {

    @Autowired
    private JavaQuestionBot javaQuestionBot;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ServiceMessagesService serviceMessagesService;

    private Logger log = LoggerFactory.getLogger(ExecutorService.class);

    // TODO: 29/10/2020 set administrators chats ids this from dataBase
    public void sendUserContactsToAdministrators(ServiceMessage serviceMessage, Long... administratorsChatsIds) {
        for (Long id : administratorsChatsIds) {
            sendUserContactsToAdministrator(serviceMessage, id);
        }
    }

    public void sendMessageToUser(ServiceMessage serviceMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(serviceMessage.getChatToken()); //todo: consider to set chat token from profile settings. what will be if I need tokens for viber, insta, ext.?
        sendMessage.setText(serviceMessage.getMessageText());
        try {
            javaQuestionBot.execute(sendMessage);
            serviceMessagesService.updateServiceMessageWithSentSuccessStatus(serviceMessage);
        } catch (Exception ex) {
            log.error("Exception while sending message to user profileId {}: message text: {}", serviceMessage.getUserProfileId(), serviceMessage.getMessageText());
            log.error(ex.getMessage());

        }

    }

    public void sendUpdateToMessageProcessor(Update update) { // TODO: 04/11/2020 move from executor to messageService
        serviceMessagesService.sendUserIncomeDataToMessageProcessorAsUpdate(update);
    }

    private void sendUserContactsToAdministrator(ServiceMessage serviceMessage, Long administratorChatId) {
        try {
            sendUserContactsAsSendContacts(serviceMessage, administratorChatId);
            log.info("1-[SUCCESS] sent userContacts as sendContacts, data {}", serviceMessage.getMessageText());
            serviceMessagesService.updateServiceMessageWithSentSuccessStatus(serviceMessage);
        } catch (Exception ex) {
            log.info("1-[FAILED] sent userContacts as sendContacts, data {}", serviceMessage.getMessageText());
            log.info(ex.getStackTrace().toString());
            try {
                sendUserContactsAsMessage(serviceMessage, administratorChatId);
                log.info("2-[SUCCESS] sent userContacts as regular message, data {}", serviceMessage.getMessageText());
                serviceMessagesService.updateServiceMessageWithSentSuccessStatus(serviceMessage);
            } catch (Exception e) {
                log.info("2-[FAILED] sent userContacts as regular message, data {}", serviceMessage.getMessageText());
                log.info(e.getStackTrace().toString());
            }
        }
    }

    private void sendUserContactsAsSendContacts(ServiceMessage serviceMessage, Long administratorChatId) throws JsonProcessingException, TelegramApiException {
        var sendContact = objectMapper.readValue(serviceMessage.getMessageText(), SendContact.class);
        var userContacts = objectMapper.readValue(serviceMessage.getMessageText(), UserContacts.class);

        sendContact.setvCard(Convertors.userContactsToVCard(userContacts));
        sendContact.setChatId(administratorChatId);

        javaQuestionBot.execute(sendContact);
    }

    private void sendUserContactsAsMessage(ServiceMessage serviceMessage, Long administratorChatId) throws JsonProcessingException, TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        var userContacts = objectMapper.readValue(serviceMessage.getMessageText(), UserContacts.class);

        sendMessage.setChatId(administratorChatId);
        sendMessage.setText(userContacts.asMessageText());

        javaQuestionBot.execute(sendMessage);
    }
}
