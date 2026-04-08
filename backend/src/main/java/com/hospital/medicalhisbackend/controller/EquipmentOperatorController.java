package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.EquipmentOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/equipmentoperators")
@CrossOrigin
@Tag(name = "设备操作人员管理")
public class EquipmentOperatorController {

    @Autowired
    private EquipmentOperatorService equipmentOperatorService;

    @GetMapping("/list")
    @Operation(summary = "获取设备操作人员列表")
    public Result<Object> getEquipmentOperatorList() {
        return Result.success(equipmentOperatorService.list());
    }
}
