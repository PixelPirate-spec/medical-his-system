package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/examinations")
@CrossOrigin
@Tag(name = "检查管理")
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @GetMapping("/list")
    @Operation(summary = "获取检查列表")
    public Result<Object> getExaminationList() {
        return Result.success(examinationService.list());
    }
}
