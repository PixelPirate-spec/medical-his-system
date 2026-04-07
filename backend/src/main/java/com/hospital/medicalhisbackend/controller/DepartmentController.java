package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin // 允许前端跨域请求
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public Object getDepartmentList() {
        // 直接调用 Service 层的 list() 方法，查出数据库里的所有科室并返回 JSON
        return departmentService.list();
    }
}