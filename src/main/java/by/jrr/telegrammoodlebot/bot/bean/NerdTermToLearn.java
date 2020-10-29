package by.jrr.telegrammoodlebot.bot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NerdTermToLearn {

    Long Id;
    String term;
    LocalDateTime timestamp;

}
