package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.common.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MockApiController {

    @GetMapping("/profile/me")
    public Result<Map<String, Object>> getProfile() {
        Map<String, Object> profile = Map.of(
                "id", 1,
                "username", "admin",
                "name", "系统管理员",
                "role", "ADMIN",
                "status", "ACTIVE",
                "enabled", true
        );
        return Result.success(profile);
    }

    @GetMapping("/department/list")
    public Result<Map<String, Object>> getDepartmentList() {
        List<Map<String, Object>> records = List.of(
                Map.of("id", 1, "code", "DEP001", "name", "内科", "status", "ACTIVE", "director", "张主任"),
                Map.of("id", 2, "code", "DEP002", "name", "外科", "status", "ACTIVE", "director", "李主任"),
                Map.of("id", 3, "code", "DEP003", "name", "儿科", "status", "ACTIVE", "director", "王主任"),
                Map.of("id", 4, "code", "DEP004", "name", "骨科", "status", "ACTIVE", "director", "赵主任"),
                Map.of("id", 5, "code", "DEP005", "name", "急诊科", "status", "ACTIVE", "director", "刘主任")
        );

        Map<String, Object> pageData = Map.of(
                "records", records,
                "total", records.size(),
                "size", 10,
                "current", 1,
                "pages", 1
        );

        return Result.success(pageData);
    }
}
