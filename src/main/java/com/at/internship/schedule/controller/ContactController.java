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

    @PostMapping("/create")
    public String create() {
        return "Creado";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "Eliminando";
    }
}
