package com.mall.admin.service;

import com.mall.admin.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.admin.result.CommonResult;

/**
 * <p>
 * 后台菜单表 服务类
 * </p>
 *
 * @author lixiang
 * @since 2023-05-25
 */
public interface MenuService extends IService<Menu> {

    CommonResult<?> menus();

}
