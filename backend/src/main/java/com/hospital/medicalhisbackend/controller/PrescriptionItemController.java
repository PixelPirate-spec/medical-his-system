package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.PrescriptionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/prescriptionitems")
@CrossOrigin
@Tag(name = "处方明细管理")
public class PrescriptionItemController {

    @Autowired
    private PrescriptionItemService prescriptionItemService;

    @GetMapping("/list")
    @Operation(summary = "获取处方明细列表")
    public Result<Object> getPrescriptionItemList() {
        return Result.success(prescriptionItemService.list());
    }
}
