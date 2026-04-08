package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hospital.medicalhisbackend.common.Result;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin // 允许前端跨域请求
@Tag(name = "科室管理")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    @Operation(summary = "获取科室列表")
    public Result<Object> getDepartmentList() {
        // 直接调用 Service 层的 list() 方法，查出数据库里的所有科室并返回 JSON
        return Result.success(departmentService.list());
    }
}