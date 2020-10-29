package by.jrr.telegrammoodlebot.proxy;

import by.jrr.telegrammoodlebot.model.ServiceMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "${message.service}")
public interface ServiceMessageProxy {

    @PostMapping("/messages")
    ServiceMessage postNewMessage(ServiceMessage message);

    @PutMapping("/messages/{uuid}")
    ServiceMessage updateMessage(@PathVariable UUID uuid, ServiceMessage message);

    @PutMapping("/messages")
    ServiceMessage updateMessage(ServiceMessage message);

    @GetMapping("/messages")
    CollectionModel<ServiceMessage> getAllMessages();

    @GetMapping("mysearch/messages")
    List<ServiceMessage> searchMessages (@RequestParam Map<String,String> allParams);

}
