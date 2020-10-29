package by.jrr.telegrammoodlebot.util;

import by.jrr.telegrammoodlebot.model.UserContacts;
import org.springframework.stereotype.Service;

public class Convertors {

    public static String userContactsToVCard(UserContacts userContacts) {
        return "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "FN:"+userContacts.getFirstName()+"\n" +
                "N:"+userContacts.getLastName()+"\n" +
                "ORG: JavaGuruStudent\n" +
                "TEL;MOBILE:"+userContacts.getPhoneNumber()+"\n" +
                "EMAIL;TYPE=INTERNET:"+userContacts.getEmail()+"\n" +
                "END:VCARD";
    }
}
