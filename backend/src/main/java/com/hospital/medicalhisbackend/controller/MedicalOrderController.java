package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.MedicalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicalorders")
@CrossOrigin
public class MedicalOrderController {

    @Autowired
    private MedicalOrderService medicalOrderService;

    @GetMapping("/list")
    public Object getMedicalOrderList() {
        return medicalOrderService.list();
    }
}
