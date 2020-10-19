package by.jrr.telegrammoodlebot.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("moodle.service")
public interface MoodleProxy{

    @GetMapping("/leads")
    String getAllLeads();
}
