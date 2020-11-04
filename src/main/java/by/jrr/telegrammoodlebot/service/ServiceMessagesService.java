package by.jrr.telegrammoodlebot.service;

import by.jrr.telegrammoodlebot.model.MessageStatus;
import by.jrr.telegrammoodlebot.model.MessageType;
import by.jrr.telegrammoodlebot.model.ServiceMessage;
import by.jrr.telegrammoodlebot.proxy.ServiceMessageProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceMessagesService {


    @Autowired
    ServiceMessageProxy serviceMessageProxy;

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
        serviceMessage.setTelegramStatus(MessageStatus.SENT);
        return serviceMessageProxy.updateMessage(serviceMessage.getId(), serviceMessage);
    }
}
