package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicalrecords")
@CrossOrigin
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/list")
    public Object getMedicalRecordList() {
        return medicalRecordService.list();
    }
}
