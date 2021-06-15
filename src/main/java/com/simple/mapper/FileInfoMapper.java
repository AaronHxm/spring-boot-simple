package com.simple.mapper;

import com.simple.common.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

import com.simple.entity.FileInfo;

/**
 * 文件信息表
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Mapper
public interface FileInfoMapper extends SuperMapper<FileInfo> {

}
