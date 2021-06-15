package com.simple.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName FileRequestDTO.java
 * @Description TODO
 * @createTime 2021年05月28日 13:08:00
 */
@ApiModel("上传文件请求接口")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileRequestDTO {

    private int type;

    private String rootPath;

    private String extendInfo;

    private String extendId;

}
