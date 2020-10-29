package by.jrr.telegrammoodlebot;

import by.jrr.telegrammoodlebot.bot.Chat;
import by.jrr.telegrammoodlebot.model.ServiceMessage;
import by.jrr.telegrammoodlebot.proxy.MoodleProxy;
import by.jrr.telegrammoodlebot.service.ExecutorService;
import by.jrr.telegrammoodlebot.service.ServiceMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    MoodleProxy moodleProxy;

    @Autowired
    ExecutorService executorService;
    @Autowired
    ServiceMessagesService serviceMessagesService;

    @GetMapping("/")
    public String base() {
        List<ServiceMessage> serviceMessageList = serviceMessagesService.getNewServiceMessages();
        if(serviceMessageList.size()>0) {
            ServiceMessage serviceMessage = serviceMessageList.get(0);
            executorService.sendUserContactsToAdministrators(serviceMessage, Chat.JRR_BY);
        }
        return "hello";
    }
}
