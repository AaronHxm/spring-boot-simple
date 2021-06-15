package com.simple.ctrl;

import com.alibaba.fastjson.JSONArray;
import java.util.List;
 
import com.simple.common.annotation.ResponseResult;

import com.simple.common.exception.BusinessException;
import com.simple.common.model.PageResult;
import com.simple.model.req.FileInfoUpdateConditionDTO;
import com.simple.model.ret.FileInfoDTO;
import com.simple.service.IFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import com.simple.model.req.FileInfoFormDTO;
import com.simple.model.req.FileInfoSearchRequestDTO;
import com.simple.model.ret.FileInfoSearchResultDTO;

/**
 * 文件信息表
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "文件信息表api")
@ResponseResult
public class FileInfoController {
    @Autowired
    private IFileInfoService fileInfoService;

    /**
     * 新增文件信息表
     * @param fileInfoFormDTO 表单数据
     */
    @ApiOperation(value = "新增文件信息表")
    @PostMapping("/fileInfos")
    public void saveOrUpdate(@ApiParam(value = "文件信息表相关信息", required = true) @RequestBody(required = true) @Validated FileInfoFormDTO fileInfoFormDTO,
                     BindingResult bindingResult) {
        this.fileInfoService.saveOrUpdate(fileInfoFormDTO);
    }




    /**
     * 根据主键更新文件信息表状态
     *
     * @param fileId 主键
     * @param status 状态
     *
     * @return
     */
    @ApiOperation(value = "根据主键更新文件信息表状态")
    @PostMapping("/fileInfos/disable")
    public void updateStatusById(@ApiParam(value = "主键ID", required = true) @RequestParam(value = "fileId", required = true) Long fileId,
                                 @ApiParam(value = "状态，0禁用、1启用、2锁定、3删除", required = true) @RequestParam(value = "status", required = true) Integer status) {
            FileInfoDTO fileInfoDTO = this.fileInfoService.selectById(fileId);
            fileInfoDTO.setStatus(status);
        this.fileInfoService.update(fileInfoDTO);
    }

    /**
     * 通过主键查询单条数据
     * @param fileId 主键
     * @return 单条数据
     */
    @ApiOperation(value = "查询文件信息表")
    @GetMapping("/fileInfosById")
    @ApiResponses({
        @ApiResponse(code = 0,message = "OK",response = FileInfoDTO.class),
    })
    public FileInfoDTO selectById(@ApiParam(value = "主键ID", required = true) @RequestParam(value = "fileId",required = true) Long fileId) {
        return this.fileInfoService.selectById(fileId);
    }

    /**
     * 通过主键列表批量查询数据
     * @param ids 主键列表
     * @return 数据列表
     */
    @ApiOperation(value = "查询文件信息表")
    @GetMapping("/fileInfos/batchIds")
    @ApiResponses({
        @ApiResponse(code = 0,message = "OK",response = FileInfoDTO.class),
    })
    public List<FileInfoDTO> listByBatchIds(@ApiParam(value = "主键ID列表", required = true) @RequestParam(value = "ids") String ids) {
      List<Long> idList = JSONArray.parseArray(ids,Long.class);

      return this.fileInfoService.listByBatchIds(idList);
    }

    /**
     * 搜索数据
     * @param searchRequestDTO 搜索条件
     * @return 数据列表
     */
    @ApiOperation(value = "搜索文件信息表")
    @GetMapping("/fileInfos/search")
    @ApiResponses({
        @ApiResponse(code = 0,message = "OK",response = FileInfoSearchResultDTO.class),
    })
    public PageResult<FileInfoSearchResultDTO> listSearchResult(@ApiParam(value = "搜索条件", required = true)
                                                                    @ModelAttribute @Validated FileInfoSearchRequestDTO searchRequestDTO, BindingResult bindingResult) throws BusinessException {
        return this.fileInfoService.listSearchResult(searchRequestDTO);
    }

   /**
       * 根据条件修改数据
       * @param updateConditionDTO 修改条件
       * @return 数据列表
       */
  @ApiOperation(value = "搜索文件信息表")
  @GetMapping("/fileInfos/updateByCondition")

  public Integer updateByCondition(@ApiParam(value = "修改条件", required = true)
  @ModelAttribute @Validated FileInfoUpdateConditionDTO updateConditionDTO,
              FileInfoFormDTO fileInfoFormDTO,
      BindingResult bindingResult) throws BusinessException {
    return this.fileInfoService.updateByCondition(fileInfoFormDTO,updateConditionDTO);
  }
}
