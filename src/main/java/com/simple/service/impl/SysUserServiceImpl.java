package com.simple.service.impl;

import java.util.List;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.simple.common.exception.BusinessException;
import com.simple.common.model.PageResult;
import com.simple.common.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;

import com.simple.mapper.SysUserStructMapper;
import com.simple.entity.SysUser;
import com.simple.mapper.SysUserMapper;
import com.simple.service.ISysUserService;
import com.simple.model.ret.SysUserDTO;
import com.simple.model.req.SysUserFormDTO;
import com.simple.model.req.SysUserSearchRequestDTO;
import com.simple.model.ret.SysUserSearchResultDTO;
import com.simple.model.req.SysUserUpdateConditionDTO;
import org.springframework.stereotype.Service;

/**
 * 文件用户
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Slf4j
@Service
public class SysUserServiceImpl extends
        BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

  /**
   * 新增和修改
   *
   * @param sysUserFormDTO)
   * @return
   */
  @Override
  public int saveOrUpdate(SysUserFormDTO sysUserFormDTO) {
      SysUser sysUser =
          SysUserStructMapper.INSTANCE.formDto2Entity(sysUserFormDTO);
    if (sysUser.getId() == null){
      return baseMapper.insert(sysUser);

    }else{
      return baseMapper.updateById(sysUser);
    }


  }

  /**
   * 根据 ID 修改
   *
   * @param id
   * @param sysUserFormDTO)
   * @return
   */
                            
  /**
   * 根据 DTO对象，更新记录
   *
   * @param sysUserDTO SysUserDTO对象
   * @return
   */
  @Override
  public int update(SysUserDTO sysUserDTO) {
      SysUser sysUser =SysUserStructMapper.INSTANCE.dto2Entity(sysUserDTO);
    return baseMapper
        .update(sysUser, new UpdateWrapper<SysUser>().eq("id", sysUser.getId()));
  }

  /**
   * 根据 ID 查询
   *
   * @param id 主键ID
   * @return
   */
  @Override
  public SysUserDTO selectById(Long id) {
      SysUser sysUser =baseMapper.selectById(id);
          SysUserDTO sysUserDTO = SysUserStructMapper.INSTANCE
        .entity2Dto(sysUser);

    return sysUserDTO;
  }

  /**
   * 查询（根据ID 批量查询）
   *
   * @param idList 主键ID列表(不能为 null 以及 empty)
   * @return
   */
  @Override
  public List<SysUserDTO> listByBatchIds(List<Long> idList) {
    List<SysUser> sysUserList = baseMapper.selectBatchIds(idList);
    List<SysUserDTO> sysUserDTOList = SysUserStructMapper.INSTANCE
        .entity2Dto(sysUserList);

    return sysUserDTOList;
  }

  /**
   * 根据 SysUserSearchRequestDTO 条件，查询全部记录（并翻页）
   *
   * @param searchRequestDTO 分页查询条件
   * @throws BusinessException
   * @return
   */
  @Override
  public PageResult<SysUserSearchResultDTO> listSearchResult(
              SysUserSearchRequestDTO searchRequestDTO) throws BusinessException {
    Page<SysUser> page = new Page<SysUser>(searchRequestDTO.getCurrentPage(),
        searchRequestDTO.getPageSize());
    QueryWrapper<SysUser> queryWrapper = createQueryWrapper(
        "com.bokun.it.model.req.SysUserSearchRequestDTO", searchRequestDTO);
    IPage<SysUser> sysUserPage = baseMapper.selectPage(page, queryWrapper);

    List<SysUser> sysUserList = sysUserPage.getRecords();
    List<SysUserSearchResultDTO> result = SysUserStructMapper.INSTANCE
        .entity2SearchResultDto(sysUserList);

    return PageResult.<SysUserSearchResultDTO>builder().list(result)
        .count(sysUserPage.getTotal())
        .totalPage(sysUserPage.getPages()).currentPage(sysUserPage.getCurrent())
        .pageSize(sysUserPage.getSize())
        .build();
  }

  /**
   * 根据 SysUserupdateConditionDTO 根据条件统一修改
   *
   * @param updateConditionDTO 根据条件统一修改
   *    * @param SysUser 修改成什么
   * @throws BusinessException
   * @return
   */
  @Override
  public int updateByCondition( SysUserFormDTO sysUserFormDTO,
            SysUserUpdateConditionDTO updateConditionDTO) throws BusinessException {

    SysUser sysUser =
      SysUserStructMapper.INSTANCE.formDto2Entity(sysUserFormDTO);
    UpdateWrapper<SysUser> updateWrapper = createUpdateWrapper(
            "com.bokun.it.model.req.SysUserUpdateConditionDTO", updateConditionDTO);

    int update = baseMapper.update( sysUser, updateWrapper);
    return update;
  }
}
