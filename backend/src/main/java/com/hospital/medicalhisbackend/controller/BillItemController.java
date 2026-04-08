package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.BillItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/billitems")
@CrossOrigin
@Tag(name = "账单明细管理")
public class BillItemController {

    @Autowired
    private BillItemService billItemService;

    @GetMapping("/list")
    @Operation(summary = "获取账单明细列表")
    public Result<Object> getBillItemList() {
        return Result.success(billItemService.list());
    }
}
