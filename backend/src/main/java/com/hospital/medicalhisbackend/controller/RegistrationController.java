package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/list")
    public Object getRegistrationList() {
        return registrationService.list();
    }
}
