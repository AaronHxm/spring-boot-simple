package com.simple.service;

import java.util.List;

import com.simple.common.exception.BusinessException;
import com.simple.common.model.PageResult;
import com.simple.common.service.IBaseService;


import com.simple.entity.SysUser;
import com.simple.model.ret.SysUserDTO;
import com.simple.model.req.SysUserFormDTO;
import com.simple.model.req.SysUserSearchRequestDTO;
import com.simple.model.ret.SysUserSearchResultDTO;
import com.simple.model.req.SysUserUpdateConditionDTO;

/**
 * 文件用户
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
public interface ISysUserService extends IBaseService<SysUser> {

  /**
   * 插入一条记录
   *
   * @param formDTO SysUserFormDTO对象
   * @return
   */
  int saveOrUpdate(SysUserFormDTO formDTO);

  /**
   * 根据 ID 修改
   *
   * @param id
   * @param sysUserFormDTO
   * @return
   */
  //  int updateById(Long id, SysUserFormDTO sysUserFormDTO);

  /**
   * 根据 DTO对象，更新记录
   *
   * @param dto DTO对象
   * @return
   */
  int update(SysUserDTO dto);

  /**
   * 根据 ID 查询
   *
   * @param id 主键ID
   * @return
   */
      SysUserDTO selectById(Long id);

  /**
   * 查询（根据ID 批量查询）
   *
   * @param idList 主键ID列表(不能为 null 以及 empty)
   * @return
   */
  List<SysUserDTO> listByBatchIds(List<Long> idList);

  /**
   * 根据 SysUserSearchRequestDTO 条件，查询全部记录（并翻页）
   *
   * @param searchRequestDTO 分页查询条件
   * @throws BusinessException
   * @return
   */
  PageResult<SysUserSearchResultDTO> listSearchResult(
              SysUserSearchRequestDTO searchRequestDTO) throws BusinessException;

  /**
   * 根据 SysUserupdateConditionDTO 根据条件统一修改
   *
   * @param SysUser 修改成什么
   * @param updateConditionDTO 根据条件统一修改
   * @throws BusinessException
   * @return
   */
  int updateByCondition( SysUserFormDTO sysUserFormDTO,SysUserUpdateConditionDTO updateConditionDTO) throws BusinessException;

}

