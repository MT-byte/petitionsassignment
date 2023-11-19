package com.assignment.maryteresaspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AppController {


    @GetMapping("/home")
    public String homePage() {
        return "home";
    }



}
