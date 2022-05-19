package com.at.internship.schedule.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@SuppressWarnings("unused")
public class ContactController {
    public ContactController () {

    }

    @GetMapping("/update")
    public String update() {
        return "Hola mundo";
    }
}
