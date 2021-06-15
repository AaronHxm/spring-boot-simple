package com.simple.service;

import java.util.List;


import com.simple.common.exception.BusinessException;
import com.simple.common.model.PageResult;
import com.simple.common.service.IBaseService;
import com.simple.entity.FileInfo;
import com.simple.model.ret.FileInfoDTO;
import com.simple.model.req.FileInfoFormDTO;
import com.simple.model.req.FileInfoSearchRequestDTO;
import com.simple.model.ret.FileInfoSearchResultDTO;
import com.simple.model.req.FileInfoUpdateConditionDTO;

/**
 * 文件信息表
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
public interface IFileInfoService extends IBaseService<FileInfo> {

    /**
     * 插入一条记录
     *
     * @param formDTO FileInfoFormDTO对象
     * @return
     */
    int saveOrUpdate(FileInfoFormDTO formDTO);

    /**
     * 根据 ID 修改
     *
     * @param id
     * @param fileInfoFormDTO
     * @return
     */
    //  int updateById(Long id, FileInfoFormDTO fileInfoFormDTO);

    /**
     * 根据 DTO对象，更新记录
     *
     * @param dto DTO对象
     * @return
     */
    int update(FileInfoDTO dto);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     * @return
     */
    FileInfoDTO selectById(Long id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     * @return
     */
    List<FileInfoDTO> listByBatchIds(List<Long> idList);

    /**
     * 根据 FileInfoSearchRequestDTO 条件，查询全部记录（并翻页）
     *
     * @param searchRequestDTO 分页查询条件
     * @return
     * @throws BusinessException
     */
    PageResult<FileInfoSearchResultDTO> listSearchResult(
            FileInfoSearchRequestDTO searchRequestDTO) throws BusinessException;

    /**
     * 根据 FileInfoupdateConditionDTO 根据条件统一修改
     *
     * @param FileInfo           修改成什么
     * @param updateConditionDTO 根据条件统一修改
     * @return
     * @throws BusinessException
     */
    int updateByCondition(FileInfoFormDTO fileInfoFormDTO, FileInfoUpdateConditionDTO updateConditionDTO) throws BusinessException;

}

