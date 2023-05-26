package com.mall.admin.service;

import com.mall.admin.entity.Admin;
import com.mall.admin.entity.AdminLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户登录日志表 服务类
 * </p>
 *
 * @author lixiang
 * @since 2023-05-26
 */
public interface AdminLoginLogService extends IService<AdminLoginLog> {

    void adminLoginLog(Admin adminInfo);

}
