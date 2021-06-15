package com.simple.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName BaseFullDTO.java
 * @Description TODO
 * @createTime 2021年06月01日 09:14:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("BaseFullDTO")
public class BaseFullDTO extends BaseDTO {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    private Long createUser;
    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    private Date updateTime;
    /**
     * 更新人
     */
    @ApiModelProperty(hidden = true)
    private Long updateUser;



    @ApiModelProperty("更新人")
    private String updateName;
    @ApiModelProperty("创建人")
    private String createName;


//
//    @ApiModelProperty("备注")
//    private String remarks;
}
