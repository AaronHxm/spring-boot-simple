package com.simple.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName FileDTO.java
 * @Description TODO
 * @createTime 2021年05月27日 14:53:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("上传返回对象")
@Builder
public class FileResponseDTO {
    /**
     * 主键
     */

    private Long fileId;
    /**
     * 第三方主键
     */
    private String extendId;
    /**
     * 文件原始名称
     */
    private String fileRealName;
    /**
     * 文件uuid名称
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件http路径
     */
    private String fileHttpPath;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 第三方扩展信息
     */
    private String extendInfo;
    /**
     * 文件上传方式
     */
    private Integer fileUploadType;
    /**
     * 文件扩展名
     */
    private String extendName;
    /**
     * 文件状态 0 存在 1 逻辑删除 2 物理删除
     */
    private Integer fileStatue;
    /**
     * 创建人
     */
    private Long createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private Long updateUser;
    /**
     * 修改时间
     */
    private Date updateTime;
}
