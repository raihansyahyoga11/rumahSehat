package TA_B_SYN_65.rumahSehat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    private String Home(){
        return "home";
    }

}
