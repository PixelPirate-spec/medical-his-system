package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin
@Tag(name = "医生管理")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/list")
    @Operation(summary = "获取医生列表")
    public Result<Object> getDoctorList() {
        return Result.success(doctorService.list());
    }
}
