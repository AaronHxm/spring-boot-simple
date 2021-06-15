package com.simple.manager;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.common.exception.BusinessException;
import com.simple.common.service.impl.BaseServiceImpl;
import com.simple.common.util.IdGenerator;
import com.simple.dto.FileRequestDTO;
import com.simple.entity.FileInfo;
import com.simple.mapper.FileInfoMapper;
import com.simple.mapper.FileInfoStructMapper;
import com.simple.model.req.FileDownloadRequestDTO;
import com.simple.model.req.FileInfoFormDTO;
import com.simple.model.ret.FileInfoDTO;
import com.simple.service.IFileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName FileManager.java
 * @Description TODO
 * @createTime 2021年06月01日 10:47:00
 */
@Service
@Slf4j
public class FileManage  extends BaseServiceImpl<FileInfoMapper, FileInfo>  {

    @Autowired
    HttpServletRequest request;
    @Value("${bokun.file.location}")
    private String uploadFileLocation;
    @Value("${bokun.file.resourceHandler}")
    private String resourceHandler;
    @Autowired
    private IFileInfoService fileInfoService;
    @Autowired
    HttpServletResponse response;

    public FileInfoDTO upload(MultipartFile file, FileRequestDTO dto) throws BusinessException {
        if (file == null || file.isEmpty()) {
            throw new BusinessException(-200,"文件不存在");
        }
        String originalFileName = file.getOriginalFilename();

        String path = uploadFileLocation + "/" + DateUtil.format(new Date(), "yyyyMMdd");
        String extendName = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = IdGenerator.getIdStr() + extendName;
        String httpPath = "/uploadFiles/" + DateUtil.format(new Date(), "yyyyMMdd") + "/" + fileName;
        //request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+
        File pfile = new File(path);
        if (!pfile.exists()) {
            pfile.mkdirs();
        }
        File saveFile = new File(path, fileName);
        try {
            file.transferTo(saveFile);//文件保存
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInfoFormDTO infoEntity = new FileInfoFormDTO();
        infoEntity.setExtendInfo(dto.getExtendInfo());
        infoEntity.setExtendId(dto.getExtendId());
        infoEntity.setFilePath(path);
        infoEntity.setFileHttpPath(httpPath); //数据库存储
        infoEntity.setFileName(fileName);
        infoEntity.setFileRealName(originalFileName);
        infoEntity.setFileStatue(0);
        infoEntity.setFileSize(file.getSize());
        infoEntity.setExtendName(extendName);
        FileInfo fileInfo = FileInfoStructMapper.INSTANCE.formDto2Entity(infoEntity);
        fileInfoService.save(fileInfo);
        return FileInfoStructMapper.INSTANCE.entity2Dto(fileInfo);
    }

    public void downFile(FileDownloadRequestDTO dto) {


        try {

            QueryWrapper<FileInfo> queryWrapper =  createQueryWrapper(
                    "com.bokun.it.model.req.FileInfoSearchRequestDTO", dto);
            List<FileInfo> list = fileInfoService.list(queryWrapper);
            List<FileInfoDTO> fileInfoDTOS = FileInfoStructMapper.INSTANCE.entity2Dto(list);
            if (CollectionUtils.isEmpty(fileInfoDTOS)) {
                throw new BusinessException(-500, "没有满足条件的文件");
            }
            if (fileInfoDTOS.size() > 1) {
                doDownManyFile(fileInfoDTOS);

            } else {
                doDownOneFile(fileInfoDTOS.get(0));
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 单文件下载
     *
     * @param dto
     */
    public void doDownOneFile(FileInfoDTO dto) {
        log.info(dto.toString());
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];
        //设置文件路径
        File file = new File(dto.getFilePath() + "/" + dto.getFileName());
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment; filename=" + new String(dto.getFileRealName().getBytes("gb2312"), "ISO8859-1"));
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 多文件下载
     *
     * @param list
     */
    public void doDownManyFile(List<FileInfoDTO> list) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        BufferedOutputStream out = new BufferedOutputStream(zip);
        String name = "";
        HashMap<String,String> hashMap = new HashMap<>();
        list.stream().forEach(dto -> {
            try {
                zip.putNextEntry(new ZipEntry(dto.getFileRealName()));
                InputStream inputStream =  new FileInputStream(new File(dto.getFilePath() + "/" + dto.getFileName()));
                BufferedInputStream bis = new BufferedInputStream(inputStream);
             //   IOUtils.write(File2byte(new File(dto.getFilePath() + "/" + dto.getFileName())), zip, "UTF-8");
                byte[] buffer = new byte[1024];
                int i = bis.read(buffer);
                while (i != -1) {
                    zip.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                IOUtils.closeQuietly();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        IOUtils.closeQuietly(zip);
        String fileName = list.get(0).getFileRealName().replaceAll(list.get(0).getExtendName(), "") + "等" + list.size() + "文件" + ".zip";
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            response.addHeader("Content-Length", "" + outputStream.toByteArray().length);
            response.setContentType("application/octet-stream; charset=UTF-8");

            IOUtils.write(outputStream.toByteArray(), response.getOutputStream());
        } catch (Exception e) {

        }
    }
    /**
     * 将文件转换成byte数组
     * @param tradeFile
     * @return
     */
    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }
}
