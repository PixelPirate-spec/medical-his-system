package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consultations")
@CrossOrigin
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @GetMapping("/list")
    public Object getConsultationList() {
        return consultationService.list();
    }
}
