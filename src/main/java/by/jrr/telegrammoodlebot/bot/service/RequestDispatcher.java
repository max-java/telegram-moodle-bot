package by.jrr.telegrammoodlebot.bot.service;//package by.jrr.telegram.bot.service;

import by.jrr.telegrammoodlebot.bot.processor.BotCommand;
import by.jrr.telegrammoodlebot.bot.processor.HelpProcessor;
import by.jrr.telegrammoodlebot.bot.processor.StartProcessor;
import by.jrr.telegrammoodlebot.service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class RequestDispatcher {
    @Autowired
    MessageService messageService;
    @Autowired
    HelpProcessor helpProcessor;
    //    @Autowired
//    SettingsProcessor settingsProcessor;
    @Autowired
    StartProcessor startProcessor;

    @Autowired
    ExecutorService executorService;

//    @Autowired
//    NerdTermProcessor nerdTermProcessor;
//    @Autowired
//    SayHelloNewUserProcessor sayHelloNewUserProcessor;
//    @Autowired
//    NoneProcessor noneProcessor;
//    @Autowired
//    SaveTermProcessor saveTermProcessor;
//    @Autowired
//    DeleteTermProcessor deleteTermProcessor;

    public void dispatch(Update update) {
        switch (getCommand(update)) {
//            case HELP:
//                messageService.sendMessage(update.getMessage(), helpProcessor.run());
//                break;
//            case START:
//                messageService.sendMessage(update.getMessage(), startProcessor.run());
//                break;
//            case SETTING:
//                messageService.sendMessage(update.getMessage(), settingsProcessor.run());
//                break;
//            case NERD_TERM:
//                nerdTermProcessor.process(update);
//                break;
//            case SAVE_TERM:
//                saveTermProcessor.process(update);
//                break;
//            case DELETE_TERM:
//                deleteTermProcessor.process(update);
//                break;
//            case SAY_HELLO:
//                sayHelloNewUserProcessor.process(update);
//                break;
            case NONE:
                executorService.sendUpdateToMessageProcessor(update);
                break;


        }
    }

    private BotCommand getCommand(Update update) {
        if (update.hasMessage()) {
//            Message message = update.getMessage();
//            if (message != null && message.hasText()) {
//                String msgText = message.getText().toLowerCase();
//                System.out.println("msgText = " + msgText);
//                if (msgText.startsWith(BotCommand.HELP.getCommand())) {
//                    return BotCommand.HELP;
//                } else if (msgText.startsWith(BotCommand.START.getCommand())) {
//                    return BotCommand.START;
//                } else if (msgText.startsWith(BotCommand.SETTING.getCommand())) {
//                    return BotCommand.SETTING;
//                } else if (msgText.startsWith(BotCommand.NERD_TERM.getCommand())) {
//                    return BotCommand.NERD_TERM;
//                } else if (msgText.startsWith(BotCommand.SAVE_TERM.getCommand())) {
//                    return BotCommand.SAVE_TERM;
//                } else if (msgText.startsWith(BotCommand.DELETE_TERM.getCommand())) {
//                    return BotCommand.DELETE_TERM;
//
//                } else if (msgText.contains(BotCommand.NERD_TERM.getCommand().substring(1))) { // TODO: 27/07/20 add chat listener
//                    return BotCommand.NERD_TERM;
//                } else if (msgText.contains(BotCommand.SAY_HELLO.getCommand())) {
//                    return BotCommand.SAY_HELLO;
//                }
//
//
//            } else if (message != null) {
//                if (message.getGroupchatCreated() != null && message.getGroupchatCreated()) {
//                    return BotCommand.SAY_HELLO;
//                } else if (message.getNewChatMembers() != null && message.getNewChatMembers().size() > 0) {
//                    return BotCommand.SAY_HELLO;
//                }
//            }
        }
        return BotCommand.NONE;
    }
}
