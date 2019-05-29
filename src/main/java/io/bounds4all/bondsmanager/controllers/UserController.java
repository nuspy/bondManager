package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.business_logic.SendConfirmationEmail;
import io.bounds4all.bondsmanager.dtos.ClientLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    SendConfirmationEmail sendConfirmationEmail;

    @RequestMapping("/register")
    public String register(@RequestParam(value="credentials") ClientLoginDto credentials) {
        //ToDo: create new Client Record
        sendConfirmationEmail.sendEmail(credentials.getEmail());
        return null;
    }

    @RequestMapping("/register")
    public String login(@RequestParam(value="credentials") ClientLoginDto credentials) {
        //ToDo: check Client credentials
        //assign a
        return null;
    }

}
