package com.mall.admin.service;

import com.mall.admin.dto.admin.request.AdminLoginRequestDto;
import com.mall.admin.dto.admin.request.AdminRegisterRequestDto;
import com.mall.admin.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.admin.result.CommonResult;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author lixiang
 * @since 2023-05-26
 */
public interface AdminService extends IService<Admin> {

    Admin getAdminByUsername(String username);

    CommonResult<?> register(AdminRegisterRequestDto adminRegisterRequestDto);

    CommonResult<?> login(AdminLoginRequestDto adminLoginRequestDto);

    UserDetails loadUserByUsername(String username);
}
