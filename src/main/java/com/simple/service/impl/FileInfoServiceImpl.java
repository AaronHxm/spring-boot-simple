package com.simple.service.impl;


import java.util.List;

import com.simple.common.exception.BusinessException;
import com.simple.common.model.PageResult;
import com.simple.common.service.impl.BaseServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.simple.service.IFileInfoService;
import lombok.extern.slf4j.Slf4j;

import com.simple.mapper.FileInfoStructMapper;
import com.simple.entity.FileInfo;
import com.simple.mapper.FileInfoMapper;
import com.simple.model.ret.FileInfoDTO;
import com.simple.model.req.FileInfoFormDTO;
import com.simple.model.req.FileInfoSearchRequestDTO;
import com.simple.model.ret.FileInfoSearchResultDTO;
import com.simple.model.req.FileInfoUpdateConditionDTO;
import org.springframework.stereotype.Service;

/**
 * 文件信息表
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Slf4j
@Service
public class FileInfoServiceImpl extends
        BaseServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {

  /**
   * 新增和修改
   *
   * @param fileInfoFormDTO)
   * @return
   */
  @Override
  public int saveOrUpdate(FileInfoFormDTO fileInfoFormDTO) {
      FileInfo fileInfo =
          FileInfoStructMapper.INSTANCE.formDto2Entity(fileInfoFormDTO);
    if (fileInfo.getId() == null){
      return baseMapper.insert(fileInfo);

    }else{
        return baseMapper.updateById(fileInfo);
    }


  }

  /**
   * 根据 ID 修改
   *
   * @param id
   * @param fileInfoFormDTO)
   * @return
   */
                            
  /**
   * 根据 DTO对象，更新记录
   *
   * @param fileInfoDTO FileInfoDTO对象
   * @return
   */
  @Override
  public int update(FileInfoDTO fileInfoDTO) {
      FileInfo fileInfo =FileInfoStructMapper.INSTANCE.dto2Entity(fileInfoDTO);
    return baseMapper
        .update(fileInfo, new UpdateWrapper<FileInfo>().eq("id", fileInfo.getId()));
  }

  /**
   * 根据 ID 查询
   *
   * @param id 主键ID
   * @return
   */
  @Override
  public FileInfoDTO selectById(Long id) {
      FileInfo fileInfo =baseMapper.selectById(id);
          FileInfoDTO fileInfoDTO = FileInfoStructMapper.INSTANCE
        .entity2Dto(fileInfo);

    return fileInfoDTO;
  }

  /**
   * 查询（根据ID 批量查询）
   *
   * @param idList 主键ID列表(不能为 null 以及 empty)
   * @return
   */
  @Override
  public List<FileInfoDTO> listByBatchIds(List<Long> idList) {
    List<FileInfo> fileInfoList = baseMapper.selectBatchIds(idList);
    List<FileInfoDTO> fileInfoDTOList = FileInfoStructMapper.INSTANCE
        .entity2Dto(fileInfoList);

    return fileInfoDTOList;
  }

  /**
   * 根据 FileInfoSearchRequestDTO 条件，查询全部记录（并翻页）
   *
   * @param searchRequestDTO 分页查询条件
   * @throws BusinessException
   * @return
   */
  @Override
  public PageResult<FileInfoSearchResultDTO> listSearchResult(
              FileInfoSearchRequestDTO searchRequestDTO) throws BusinessException {
    Page<FileInfo> page = new Page<FileInfo>(searchRequestDTO.getCurrentPage(),
        searchRequestDTO.getPageSize());
    QueryWrapper<FileInfo> queryWrapper = createQueryWrapper(
        "com.bokun.it.model.req.FileInfoSearchRequestDTO", searchRequestDTO);
    IPage<FileInfo> fileInfoPage = baseMapper.selectPage(page, queryWrapper);

    List<FileInfo> fileInfoList = fileInfoPage.getRecords();
    List<FileInfoSearchResultDTO> result = FileInfoStructMapper.INSTANCE
        .entity2SearchResultDto(fileInfoList);

    return PageResult.<FileInfoSearchResultDTO>builder().list(result)
        .count(fileInfoPage.getTotal())
        .totalPage(fileInfoPage.getPages()).currentPage(fileInfoPage.getCurrent())
        .pageSize(fileInfoPage.getSize())
        .build();
  }

  /**
   * 根据 FileInfoupdateConditionDTO 根据条件统一修改
   *
   * @param updateConditionDTO 根据条件统一修改
   *    * @param FileInfo 修改成什么
   * @throws BusinessException
   * @return
   */
  @Override
  public int updateByCondition( FileInfoFormDTO fileInfoFormDTO,
            FileInfoUpdateConditionDTO updateConditionDTO) throws BusinessException {

    FileInfo fileInfo =
      FileInfoStructMapper.INSTANCE.formDto2Entity(fileInfoFormDTO);
    UpdateWrapper<FileInfo> updateWrapper = createUpdateWrapper(
            "com.bokun.it.model.req.FileInfoUpdateConditionDTO", updateConditionDTO);

    int update = baseMapper.update( fileInfo, updateWrapper);
    return update;
  }
}
