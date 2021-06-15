package com.simple.ctrl;

import com.alibaba.fastjson.JSONArray;
import java.util.List;


import com.simple.common.annotation.ResponseResult;
import com.simple.common.exception.BusinessException;
import com.simple.common.model.PageResult;
import com.simple.model.req.SysUserUpdateConditionDTO;

import com.simple.service.ISysUserService;
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


import com.simple.model.ret.SysUserDTO;
import com.simple.model.req.SysUserFormDTO;
import com.simple.model.req.SysUserSearchRequestDTO;
import com.simple.model.ret.SysUserSearchResultDTO;

/**
 * 文件用户
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "文件用户api")
@ResponseResult
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 新增文件用户
     * @param sysUserFormDTO 表单数据
     */
    @ApiOperation(value = "新增文件用户")
    @PostMapping("/sysUsers")
    public void saveOrUpdate(@ApiParam(value = "文件用户相关信息", required = true) @RequestBody(required = true) @Validated SysUserFormDTO sysUserFormDTO,
                     BindingResult bindingResult) {
        this.sysUserService.saveOrUpdate(sysUserFormDTO);
    }




    /**
     * 根据主键更新文件用户状态
     *
     * @param userId 主键
     * @param status 状态
     *
     * @return
     */
    @ApiOperation(value = "根据主键更新文件用户状态")
    @PostMapping("/sysUsers/disable")
    public void updateStatusById(@ApiParam(value = "主键ID", required = true) @RequestParam(value = "userId", required = true) Long userId,
                                 @ApiParam(value = "状态，0禁用、1启用、2锁定、3删除", required = true) @RequestParam(value = "status", required = true) Integer status) {
            SysUserDTO sysUserDTO = this.sysUserService.selectById(userId);
            sysUserDTO.setStatus(status);
        this.sysUserService.update(sysUserDTO);
    }

    /**
     * 通过主键查询单条数据
     * @param userId 主键
     * @return 单条数据
     */
    @ApiOperation(value = "查询文件用户")
    @GetMapping("/sysUsersById")
    @ApiResponses({
        @ApiResponse(code = 0,message = "OK",response = SysUserDTO.class),
    })
    public SysUserDTO selectById(@ApiParam(value = "主键ID", required = true) @RequestParam(value = "userId",required = true) Long userId) {
        return this.sysUserService.selectById(userId);
    }

    /**
     * 通过主键列表批量查询数据
     * @param ids 主键列表
     * @return 数据列表
     */
    @ApiOperation(value = "查询文件用户")
    @GetMapping("/sysUsers/batchIds")
    @ApiResponses({
        @ApiResponse(code = 0,message = "OK",response = SysUserDTO.class),
    })
    public List<SysUserDTO> listByBatchIds(@ApiParam(value = "主键ID列表", required = true) @RequestParam(value = "ids") String ids) {
      List<Long> idList = JSONArray.parseArray(ids,Long.class);

      return this.sysUserService.listByBatchIds(idList);
    }

    /**
     * 搜索数据
     * @param searchRequestDTO 搜索条件
     * @return 数据列表
     */
    @ApiOperation(value = "搜索文件用户")
    @GetMapping("/sysUsers/search")
    @ApiResponses({
        @ApiResponse(code = 0,message = "OK",response = SysUserSearchResultDTO.class),
    })
    public PageResult<SysUserSearchResultDTO> listSearchResult(@ApiParam(value = "搜索条件", required = true)
                                                                    @ModelAttribute @Validated SysUserSearchRequestDTO searchRequestDTO, BindingResult bindingResult) throws BusinessException {
        return this.sysUserService.listSearchResult(searchRequestDTO);
    }

   /**
       * 根据条件修改数据
       * @param updateConditionDTO 修改条件
       * @return 数据列表
       */
  @ApiOperation(value = "搜索文件用户")
  @GetMapping("/sysUsers/updateByCondition")

  public Integer updateByCondition(@ApiParam(value = "修改条件", required = true)
  @ModelAttribute @Validated SysUserUpdateConditionDTO updateConditionDTO,
              SysUserFormDTO sysUserFormDTO,
      BindingResult bindingResult) throws BusinessException {
    return this.sysUserService.updateByCondition(sysUserFormDTO,updateConditionDTO);
  }
}
