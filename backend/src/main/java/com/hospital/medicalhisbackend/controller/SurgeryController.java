package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/surgeries")
@CrossOrigin
public class SurgeryController {

    @Autowired
    private SurgeryService surgeryService;

    @GetMapping("/list")
    public Object getSurgeryList() {
        return surgeryService.list();
    }
}
