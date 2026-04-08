package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.InPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inpatients")
@CrossOrigin
public class InPatientController {

    @Autowired
    private InPatientService inPatientService;

    @GetMapping("/list")
    public Object getInPatientList() {
        return inPatientService.list();
    }
}
