package by.jrr.telegrammoodlebot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserContacts {
    String email = "sample@emao.by";
    String firstName = "firstName";
    String lastName = "lastName";
    String phoneNumber = "+375447577777";

    public String asMessageText() {
        return String.format("%s\n%s\n%s\n%s", firstName, lastName, email, phoneNumber);
    }
}
