package by.jrr.telegrammoodlebot.bot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NerdTermLibrary {

    Long Id;
    String term;
    String definition;
}
