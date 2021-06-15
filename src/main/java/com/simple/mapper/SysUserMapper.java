package com.simple.mapper;

import com.simple.common.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

import com.simple.entity.SysUser;

/**
 * 文件用户
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Mapper
public interface SysUserMapper extends SuperMapper<SysUser> {

}
