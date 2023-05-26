package com.mall.admin.mapper;

import com.mall.admin.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author lixiang
 * @since 2023-05-26
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
