package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin
@Tag(name = "患者管理")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    @Operation(summary = "获取患者列表")
    public Result<Object> getPatientList() {
        return Result.success(patientService.list());
    }
}
