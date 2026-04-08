package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.DoctorDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctordepartments")
@CrossOrigin
public class DoctorDepartmentController {

    @Autowired
    private DoctorDepartmentService doctorDepartmentService;

    @GetMapping("/list")
    public Object getDoctorDepartmentList() {
        return doctorDepartmentService.list();
    }
}
