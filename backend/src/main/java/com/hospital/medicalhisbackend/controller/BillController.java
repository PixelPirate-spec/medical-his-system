package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin
@Tag(name = "账单管理")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/list")
    @Operation(summary = "获取账单列表")
    public Result<Object> getBillList() {
        return Result.success(billService.list());
    }
}
