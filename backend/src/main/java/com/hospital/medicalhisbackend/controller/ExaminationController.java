package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/examinations")
@CrossOrigin
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @GetMapping("/list")
    public Object getExaminationList() {
        return examinationService.list();
    }
}
