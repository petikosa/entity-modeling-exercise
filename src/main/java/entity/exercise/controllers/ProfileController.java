package entity.exercise.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ProfileController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from the entity app!";
    }

}