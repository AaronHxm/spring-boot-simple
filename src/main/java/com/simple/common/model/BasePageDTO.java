package com.simple.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName BasePageDTO.java
 * @Description TODO
 * @createTime 2021年06月01日 09:04:00
 */
@Data
@ApiModel("BasePageDTO")
public class BasePageDTO extends BaseDTO {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 当前页，默认第1页
     */
    @ApiModelProperty("当前页，默认第1页")
    private Integer currentPage;
    /**
     * 每页显示条数，默认10条
     */
    @ApiModelProperty("每页显示条数，默认10条")
    private Integer pageSize;

    @ApiModelProperty("更新人")
    private String updateName;
    @ApiModelProperty("创建人")

    private String createName;


    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    private Long createUser;
}
