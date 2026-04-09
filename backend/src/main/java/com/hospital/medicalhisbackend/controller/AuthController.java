package com.hospital.medicalhisbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hospital.medicalhisbackend.common.JwtUtils;
import com.hospital.medicalhisbackend.common.Result;
import com.hospital.medicalhisbackend.entity.User;
import com.hospital.medicalhisbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Tag(name = "鉴权管理")
public class AuthController {

    @Autowired
    private UserService userService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result<User> register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("用户名和密码不能为空");
        }

        // 检查用户名是否已存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        if (userService.getOne(queryWrapper) != null) {
            return Result.error("用户名已存在");
        }

        // 加密密码并设置默认属性
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoleName() == null || user.getRoleName().trim().isEmpty()) {
            user.setRoleName("PATIENT");
        }

        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userService.save(user);

        // 为了安全，不返回密码
        user.setPassword(null);
        return Result.success(user);
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<Map<String, Object>> login(@RequestBody User loginUser) {
        if (loginUser.getUsername() == null || loginUser.getPassword() == null) {
            return Result.error("用户名和密码不能为空");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginUser.getUsername());
        User user = userService.getOne(queryWrapper);

        if (user == null || !passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            return Result.error(401, "账号或密码错误");
        }

        // 登录成功，生成 Token
        String token = JwtUtils.generateToken(user.getId(), user.getRoleName());

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);

        // 过滤密码再返回用户信息
        user.setPassword(null);
        data.put("user", user);

        return Result.success(data);
    }
}
