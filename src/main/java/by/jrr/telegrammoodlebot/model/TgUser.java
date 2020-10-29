package by.jrr.telegrammoodlebot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
@NoArgsConstructor
@Deprecated
public class TgUser extends User {
    private Integer id;
    private Long profileId;

    private String firstName;
    private Boolean isBot;
    private String lastName;
    private String userName;
    private String languageCode;

    public TgUser(User user) {
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.isBot = user.getBot();
            this.lastName = user.getLastName();
            this.userName = user.getUserName();
            this.languageCode = user.getLanguageCode();
    }

}
