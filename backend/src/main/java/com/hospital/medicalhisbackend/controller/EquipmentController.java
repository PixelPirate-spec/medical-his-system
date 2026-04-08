package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/equipments")
@CrossOrigin
@Tag(name = "医疗设备管理")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/list")
    @Operation(summary = "获取医疗设备列表")
    public Result<Object> getEquipmentList() {
        return Result.success(equipmentService.list());
    }
}
