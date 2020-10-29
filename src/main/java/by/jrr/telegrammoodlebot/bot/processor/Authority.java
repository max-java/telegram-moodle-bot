package by.jrr.telegrammoodlebot.bot.processor;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class Authority {

    public boolean check(Message msg) {
        return msg.getFrom().getUserName() != null && msg.getFrom().getUserName().equals("jrrby");
    }

}
