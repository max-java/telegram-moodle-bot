package by.jrr.telegrammoodlebot.service;

import by.jrr.telegrammoodlebot.bot.service.MessageService;
import by.jrr.telegrammoodlebot.model.MessageStatus;
import by.jrr.telegrammoodlebot.model.MessageType;
import by.jrr.telegrammoodlebot.model.ServiceMessage;
import by.jrr.telegrammoodlebot.proxy.ServiceMessageProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceMessagesService {
    //todo: consider to rename this service, because of message entity in telegram

    Logger logger = LoggerFactory.getLogger(ServiceMessagesService.class);

    @Autowired
    ServiceMessageProxy serviceMessageProxy;

    @Autowired
    ObjectMapper objectMapper;

    public List<ServiceMessage> getAllServiceMessages() {
        CollectionModel<ServiceMessage> allMessages = serviceMessageProxy.getAllMessages();
        return List.copyOf(allMessages.getContent());
    }

    public List<ServiceMessage> getNewUserContactsForTelegram() {
        Map<String, String> params = new HashMap<>();
        params.put("telegramStatus", MessageStatus.NEW.name());
        params.put("type", MessageType.CONTACT_DATA.name());
        return serviceMessageProxy.getNewUserContactsForTelegram(params);
    }

    public ServiceMessage updateServiceMessageWithSentSuccessStatus(ServiceMessage serviceMessage) {
//        todo replace this with single field update endpoint.
        serviceMessage.setTelegramStatus(MessageStatus.SENT);
        return serviceMessageProxy.updateMessage(serviceMessage.getId(), serviceMessage);
    }

    public ServiceMessage sendUserIncomeDataToMessageProcessorAsUpdate(Update update) {
        ServiceMessage serviceMessage = new ServiceMessage();
        try {
            serviceMessage.setTelegramStatus(MessageStatus.NEW);
            serviceMessage.setMessageType(MessageType.INCOME_DATA);
            serviceMessage.setChatToken(String.valueOf(update.getMessage().getChatId()));
            serviceMessage.setMessageText(objectMapper.writeValueAsString(update));
            serviceMessageProxy.postNewMessage(serviceMessage);
        } catch (Exception ex) {
            logger.error("Exception while sending update to messageProcessor, {}", update);
            ex.printStackTrace();
        }
        return serviceMessage;
    }

    public List<ServiceMessage> getNewMessageForTelegram() {
        return serviceMessageProxy.getNewMessageForTelegram();
    }
}
