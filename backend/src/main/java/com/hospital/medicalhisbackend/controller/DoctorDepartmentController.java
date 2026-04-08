package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.DoctorDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/doctordepartments")
@CrossOrigin
@Tag(name = "科室医生关系管理")
public class DoctorDepartmentController {

    @Autowired
    private DoctorDepartmentService doctorDepartmentService;

    @GetMapping("/list")
    @Operation(summary = "获取科室医生关系列表")
    public Result<Object> getDoctorDepartmentList() {
        return Result.success(doctorDepartmentService.list());
    }
}
