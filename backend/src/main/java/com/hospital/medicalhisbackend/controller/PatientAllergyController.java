package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.PatientAllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/patientallergies")
@CrossOrigin
@Tag(name = "患者过敏史管理")
public class PatientAllergyController {

    @Autowired
    private PatientAllergyService patientAllergyService;

    @GetMapping("/list")
    @Operation(summary = "获取患者过敏史列表")
    public Result<Object> getPatientAllergyList() {
        return Result.success(patientAllergyService.list());
    }
}
