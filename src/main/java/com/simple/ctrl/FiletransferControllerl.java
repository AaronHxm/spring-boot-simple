package com.simple.ctrl;

import com.simple.common.annotation.ResponseResult;
import com.simple.common.exception.BusinessException;
import com.simple.dto.FileRequestDTO;
import com.simple.dto.FileResponseDTO;
import com.simple.manager.FileManage;
import com.simple.model.req.FileDownloadRequestDTO;
import com.simple.model.ret.FileInfoDTO;
import com.simple.model.ret.FileInfoSearchResultDTO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName FiletransferControllerl.java
 * @Description TODO
 * @createTime 2021年05月27日 11:08:00
 */
@Slf4j
@Api(value = "filetransfer", tags = "该接口为文件传输接口，主要用来做文件的上传和下载")
@RestController
@ResponseResult
@RequestMapping("/filetransfer")
public class FiletransferControllerl {

    @Autowired
    private FileManage fileManager;

    @PostMapping("upload")
    @ApiOperation(value = "上传接口", response = FileResponseDTO.class)

    public FileInfoDTO upload(MultipartFile multipartFile, FileRequestDTO dto) throws BusinessException {
        return fileManager.upload(multipartFile, dto);
    }

    /**
     * 搜索数据
     *
     * @param dto 搜索条件
     * @return 下載接口
     */
    @ApiOperation(value = "下载接口")
    @GetMapping("/download")
    @ApiResponses({
            @ApiResponse(code = 0, message = "OK", response = FileInfoSearchResultDTO.class),
    })
    public void listSearchResult(@ApiParam(value = "下载条件条件", required = true)
                                 @ModelAttribute @Validated FileDownloadRequestDTO dto, BindingResult bindingResult) throws BusinessException {
        fileManager.downFile(dto);
    }


}
