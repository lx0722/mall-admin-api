package com.mall.admin.dto.admin.response;

import lombok.Data;

import java.util.List;

@Data
public class MenusResponseDto {

    private Long id;

    private Long parentId;

    private String title;

    private String name;

    private String icon;

    private Integer level;

    private Integer sort;

    private Integer hidden;

    private List<MenusResponseDto> children;

}
