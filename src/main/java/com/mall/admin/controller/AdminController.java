package com.mall.admin.controller;


import com.mall.admin.dto.admin.request.AdminLoginRequestDto;
import com.mall.admin.dto.admin.request.AdminRegisterRequestDto;
import com.mall.admin.result.CommonResult;
import com.mall.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author lixiang
 * @since 2023-05-26
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody AdminRegisterRequestDto adminRegisterRequestDto){
        return adminService.register(adminRegisterRequestDto);
    }

    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody AdminLoginRequestDto adminLoginRequestDto){
        return adminService.login(adminLoginRequestDto);
    }

}

