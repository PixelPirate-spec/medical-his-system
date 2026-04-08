package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin
@Tag(name = "挂号管理")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/list")
    @Operation(summary = "获取挂号列表")
    public Result<Object> getRegistrationList() {
        return Result.success(registrationService.list());
    }
}
