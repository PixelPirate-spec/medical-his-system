package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.PatientAllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patientallergies")
@CrossOrigin
public class PatientAllergyController {

    @Autowired
    private PatientAllergyService patientAllergyService;

    @GetMapping("/list")
    public Object getPatientAllergyList() {
        return patientAllergyService.list();
    }
}
