package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/surgeries")
@CrossOrigin
@Tag(name = "手术管理")
public class SurgeryController {

    @Autowired
    private SurgeryService surgeryService;

    @GetMapping("/list")
    @Operation(summary = "获取手术列表")
    public Result<Object> getSurgeryList() {
        return Result.success(surgeryService.list());
    }
}
