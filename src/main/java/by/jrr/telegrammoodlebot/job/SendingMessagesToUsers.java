package by.jrr.telegrammoodlebot.job;

import by.jrr.telegrammoodlebot.model.ServiceMessage;
import by.jrr.telegrammoodlebot.service.ExecutorService;
import by.jrr.telegrammoodlebot.service.ServiceMessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/** this is to continuously
 * checking message service for new messages to deliver,
 * pick one,
 * deliver one,
 * and update status depend on delivery result
 **/

@Service
public class SendingMessagesToUsers {

    Logger logger = LoggerFactory.getLogger(SendingMessagesToUsers.class);

    @Autowired
    ServiceMessagesService serviceMessagesService;

    @Autowired
    ExecutorService executorService;

    @Scheduled(fixedRate = 5000) // TODO: 02/11/2020 this timing should be reconsidered
    public void run() {
        logger.debug("Checking for new messages");
        List<ServiceMessage> serviceMessageList = serviceMessagesService.getNewMessageForTelegram();
        if (serviceMessageList.size() > 0) {
            logger.debug("New message found, initiating delivery");
            ServiceMessage serviceMessage = serviceMessageList.get(0);
            executorService.sendMessageToUser(serviceMessage);
        } else {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
