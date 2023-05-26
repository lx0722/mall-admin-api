package com.mall.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.admin.dto.admin.response.MenusResponseDto;
import com.mall.admin.entity.Menu;
import com.mall.admin.mapper.MenuMapper;
import com.mall.admin.result.CommonResult;
import com.mall.admin.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author lixiang
 * @since 2023-05-25
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public CommonResult<?> menus() {
        List<Menu> menuList = this.list();
        ModelMapper modelMapper = new ModelMapper();
        List<MenusResponseDto> menuDto = menuList.stream().map(menu -> modelMapper.map(menu, MenusResponseDto.class)).collect(Collectors.toList());
        List<MenusResponseDto> menusResponseDto = menuDto.stream().filter(menu -> menu.getLevel() == 0)
                .map(parentMenu -> {
                    parentMenu.setChildren(getChildren(menuDto, parentMenu));
                    return parentMenu;
                }).collect(Collectors.toList());
        return CommonResult.success(menusResponseDto);
    }

    private List<MenusResponseDto> getChildren(List<MenusResponseDto> menuList, MenusResponseDto parentMenu) {
        return menuList.stream().filter(menu -> menu.getParentId().equals(parentMenu.getId()))
                .map(menu -> {
                    menu.setChildren(getChildren(menuList, menu));
                    return menu;
                }).collect(Collectors.toList());
    }
}
