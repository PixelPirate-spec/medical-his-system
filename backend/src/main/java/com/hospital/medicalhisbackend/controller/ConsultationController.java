package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/consultations")
@CrossOrigin
@Tag(name = "就诊管理")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @GetMapping("/list")
    @Operation(summary = "获取就诊列表")
    public Result<Object> getConsultationList() {
        return Result.success(consultationService.list());
    }
}
