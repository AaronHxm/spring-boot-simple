package com.simple.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.simple.common.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件信息表
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文件信息表")
@TableName("file_info")
public class FileInfo extends BaseEntity {
    /**
     * 第三方主键
     */
    @ApiModelProperty("第三方主键")
    private String extendId;
    /**
     * 文件原始名称
     */
    @ApiModelProperty("文件原始名称")
    private String fileRealName;
    /**
     * 文件uuid名称
     */
    @ApiModelProperty("文件uuid名称")
    private String fileName;
    /**
     * 文件路径
     */
    @ApiModelProperty("文件路径")
    private String filePath;
    /**
     * 文件http路径
     */
    @ApiModelProperty("文件http路径")
    private String fileHttpPath;
    /**
     * 文件大小
     */
    @ApiModelProperty("文件大小")
    private Integer fileSize;
    /**
     * 第三方扩展信息
     */
    @ApiModelProperty("第三方扩展信息")
    private String extendInfo;
    /**
     * 文件上传方式
     */
    @ApiModelProperty("文件上传方式")
    private Integer fileUploadType;
    /**
     * 文件扩展名
     */
    @ApiModelProperty("文件扩展名")
    private String extendName;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Long createUser;
    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private Long updateUser;
}