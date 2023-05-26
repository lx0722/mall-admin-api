package com.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.admin.config.securityConfig.JwtTokenUtil;
import com.mall.admin.dto.admin.request.AdminLoginRequestDto;
import com.mall.admin.dto.admin.request.AdminRegisterRequestDto;
import com.mall.admin.entity.Admin;
import com.mall.admin.exception.ApiException;
import com.mall.admin.mapper.AdminMapper;
import com.mall.admin.result.CommonResult;
import com.mall.admin.service.AdminLoginLogService;
import com.mall.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author lixiang
 * @since 2023-05-26
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminLoginLogService adminLoginLogService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUsername,username));
    }

    @Override
    public CommonResult<?> register(AdminRegisterRequestDto adminRegisterRequestDto) {
        Admin adminInfo = adminMapper.selectOne(new LambdaQueryWrapper<>(Admin.class).eq(Admin::getUsername, adminRegisterRequestDto.getUsername()));
        if(ObjectUtil.isNotEmpty(adminInfo)){
            throw new ApiException("账号已注册");
        }
        Admin admin = new Admin();
        BeanUtil.copyProperties(adminRegisterRequestDto,admin);
        String password = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(password);
        adminMapper.insert(admin);
        return CommonResult.success(null,"注册成功");
    }

    @Override
    public CommonResult<?> login(AdminLoginRequestDto adminLoginRequestDto) {
        Admin adminInfo = adminMapper.selectOne(new LambdaQueryWrapper<>(Admin.class).eq(Admin::getUsername, adminLoginRequestDto.getUsername()));
        if(ObjectUtil.isEmpty(adminInfo)){
            throw new ApiException("账号不存在");
        }
        if(!adminInfo.isEnabled()){
            throw new ApiException("账号被禁用");
        }
        if(passwordEncoder.matches(adminInfo.getPassword(),adminLoginRequestDto.getPassword())){
            throw new ApiException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminInfo, null, adminInfo.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(adminInfo);
        adminLoginLogService.adminLoginLog(adminInfo);
        return CommonResult.success(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = adminInfo(username);
        if(ObjectUtil.isEmpty(admin))
            throw new ApiException("账号不存在");
        return admin;
    }

    private Admin adminInfo(String username){
        Admin admin = getOne(new LambdaQueryWrapper<>(Admin.class).eq(Admin::getUsername, username));
        return admin;
    }

}
