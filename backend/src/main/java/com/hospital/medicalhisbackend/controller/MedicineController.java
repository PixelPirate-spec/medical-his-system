package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin
@Tag(name = "药品管理")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/list")
    @Operation(summary = "获取药品列表")
    public Result<Object> getMedicineList() {
        return Result.success(medicineService.list());
    }
}
