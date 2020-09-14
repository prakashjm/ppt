package com.xyz.mydiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.xyz.mydiary.service.EmailService;

@Controller
public class MyController {

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/sendmail")
    public String sendmail() {

        emailService.sendMail("prakjm@gmail.com", "Test Subject", "Test mail");

        return "emailsent";
    }
}   