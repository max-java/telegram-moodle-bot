package by.jrr.telegrammoodlebot;

import by.jrr.telegrammoodlebot.proxy.MoodleProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @Autowired
    MoodleProxy moodleProxy;

    @GetMapping("/")
    public String base() {
        moodleProxy.getAllLeads();
        return "hello";
    }
}
