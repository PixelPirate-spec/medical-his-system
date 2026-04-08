package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/wards")
@CrossOrigin
@Tag(name = "病房管理")
public class WardController {

    @Autowired
    private WardService wardService;

    @GetMapping("/list")
    @Operation(summary = "获取病房列表")
    public Result<Object> getWardList() {
        return Result.success(wardService.list());
    }
}
