package com.simple.mapper;

import java.util.List;


import com.simple.model.ret.FileInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Builder;

import com.simple.entity.FileInfo;
import com.simple.model.req.FileInfoFormDTO;
import com.simple.model.ret.FileInfoSearchResultDTO;

/**
 * 文件信息表
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface FileInfoStructMapper {

        FileInfoStructMapper INSTANCE = Mappers.getMapper(FileInfoStructMapper.class);

        @Mappings({
                @Mapping(source = "id", target = "fileInfoId")
        })
        FileInfoDTO entity2Dto(FileInfo entity);

        List<FileInfoDTO> entity2Dto(List<FileInfo> entityList);

        FileInfo dto2Entity(FileInfoDTO dto);

        List<FileInfo> dto2Entity(List<FileInfoDTO> dtoList);

        FileInfo formDto2Entity(FileInfoFormDTO formDTO);

        void updateEntityFromDto(FileInfoFormDTO formDTO, @MappingTarget FileInfo entity);

        @Mappings({
                @Mapping(source = "id", target = "fileInfoId")
        })
        FileInfoSearchResultDTO entity2SearchResultDto(FileInfo entity);

        List<FileInfoSearchResultDTO> entity2SearchResultDto(List<FileInfo> entityList);
}
