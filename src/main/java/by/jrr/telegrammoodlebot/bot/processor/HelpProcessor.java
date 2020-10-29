package by.jrr.telegrammoodlebot.bot.processor;

import org.springframework.stereotype.Service;

@Service
public class HelpProcessor implements Processor {

    @Override
    public String run() {
        return "Magic is happening";
    }
}
