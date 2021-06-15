package com.simple.model.req;


import com.simple.common.model.BasePageDTO;

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
@ApiModel("FileInfoSearchRequestDTO")
public class FileInfoSearchRequestDTO extends BasePageDTO {

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
     * 文件状态 0 存在 1 逻辑删除 2 物理删除
     */
    @ApiModelProperty("文件状态 0 存在 1 逻辑删除 2 物理删除")
    private Integer status;

}