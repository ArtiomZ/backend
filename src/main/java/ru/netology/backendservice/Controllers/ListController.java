package ru.netology.backendservice.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ListController {

    @PostMapping
    public String hello() {
        return "HelloPost";
    }

    @GetMapping
    public String hello2() {
        return "HelloGet";
    }


}
