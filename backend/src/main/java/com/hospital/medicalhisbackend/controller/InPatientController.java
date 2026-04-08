package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.InPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/inpatients")
@CrossOrigin
@Tag(name = "住院管理")
public class InPatientController {

    @Autowired
    private InPatientService inPatientService;

    @GetMapping("/list")
    @Operation(summary = "获取住院列表")
    public Result<Object> getInPatientList() {
        return Result.success(inPatientService.list());
    }
}
