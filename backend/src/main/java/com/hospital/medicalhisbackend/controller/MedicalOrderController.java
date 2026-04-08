package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.MedicalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/medicalorders")
@CrossOrigin
@Tag(name = "医嘱管理")
public class MedicalOrderController {

    @Autowired
    private MedicalOrderService medicalOrderService;

    @GetMapping("/list")
    @Operation(summary = "获取医嘱列表")
    public Result<Object> getMedicalOrderList() {
        return Result.success(medicalOrderService.list());
    }
}
