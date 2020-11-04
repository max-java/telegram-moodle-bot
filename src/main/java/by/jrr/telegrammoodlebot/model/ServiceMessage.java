package by.jrr.telegrammoodlebot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceMessage {
    private String uuid;
    private LocalDateTime timeStamp;
    private LocalDateTime lastUpdate;

    String chatToken;
    Long userProfileId;


    private MessageStatus telegramStatus;
    private MessageStatus eMailStatus;

    private String messageText;
    private MessageType messageType;
}


