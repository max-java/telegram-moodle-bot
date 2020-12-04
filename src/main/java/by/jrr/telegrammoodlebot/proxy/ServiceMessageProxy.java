package by.jrr.telegrammoodlebot.proxy;

import by.jrr.telegrammoodlebot.model.Endpoint;
import by.jrr.telegrammoodlebot.model.ServiceMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "${message.service}")
public interface ServiceMessageProxy {
//    todo consider rename

    @PostMapping("/messages")
    ServiceMessage postNewMessage(ServiceMessage message);

    @PutMapping("/messages/{id}")
    ServiceMessage updateMessage(@PathVariable Long id, ServiceMessage message);

    @PutMapping("/messages")
    ServiceMessage updateMessage(ServiceMessage message);

    @GetMapping("/messages")
    CollectionModel<ServiceMessage> getAllMessages();

    @GetMapping(Endpoint.NEW_USER_CONTACTS_FOR_TELEGRAM)
    List<ServiceMessage> getNewUserContactsForTelegram (@RequestParam Map<String,String> allParams);

    @GetMapping(Endpoint.NEW_MESSAGE_FOR_TELEGRAM)
    List<ServiceMessage> getNewMessageForTelegram ();


}
