package com.at.internship.schedule.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    public ContactController () {

    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @PostMapping("/create")
    public String create() {
        return "create";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "delete";
    }
}