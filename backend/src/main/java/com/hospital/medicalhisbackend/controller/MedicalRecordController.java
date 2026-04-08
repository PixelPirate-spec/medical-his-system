package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/medicalrecords")
@CrossOrigin
@Tag(name = "病历管理")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/list")
    @Operation(summary = "获取病历列表")
    public Result<Object> getMedicalRecordList() {
        return Result.success(medicalRecordService.list());
    }
}
