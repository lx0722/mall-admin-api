package com.mall.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mall.admin.entity.Admin;
import com.mall.admin.entity.AdminLoginLog;
import com.mall.admin.mapper.AdminLoginLogMapper;
import com.mall.admin.service.AdminLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.admin.utils.RequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <p>
 * 后台用户登录日志表 服务实现类
 * </p>
 *
 * @author lixiang
 * @since 2023-05-26
 */
@Service
public class AdminLoginLogServiceImpl extends ServiceImpl<AdminLoginLogMapper, AdminLoginLog> implements AdminLoginLogService {

    @Override
    public void adminLoginLog(Admin adminInfo) {
        if(ObjectUtil.isEmpty(adminInfo))
            return;
        AdminLoginLog adminLoginLog = new AdminLoginLog();
        adminLoginLog.setId(adminInfo.getId());
        adminLoginLog.setCreateTime(DateUtil.date());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        adminLoginLog.setIp(RequestUtil.getRequestIp(requestAttributes.getRequest()));
        save(adminLoginLog);
    }
}
