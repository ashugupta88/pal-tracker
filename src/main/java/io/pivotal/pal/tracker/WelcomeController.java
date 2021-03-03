package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    private String name;


    public WelcomeController(@Value("${welcome.message}") String name) {
        this.name=name;
    }

    @GetMapping("/")
    public String sayHello() {
        return name;
    }


}