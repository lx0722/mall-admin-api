package com.mall.admin.controller;


import com.mall.admin.result.CommonResult;
import com.mall.admin.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台菜单表 前端控制器
 * </p>
 *
 * @author lixiang
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/v1")
@Api("menuController")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    @ApiOperation("菜单列表")
    public CommonResult<?> Menus(){
        return menuService.menus();
    }

}

