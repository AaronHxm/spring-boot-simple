package com.simple.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Builder;

import com.simple.entity.SysUser;
import com.simple.model.ret.SysUserDTO;
import com.simple.model.req.SysUserFormDTO;
import com.simple.model.ret.SysUserSearchResultDTO;

/**
 * 文件用户
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysUserStructMapper {

        SysUserStructMapper INSTANCE = Mappers.getMapper(SysUserStructMapper.class);

        @Mappings({
                @Mapping(source = "id", target = "sysUserId")
        })
        SysUserDTO entity2Dto(SysUser entity);

        List<SysUserDTO> entity2Dto(List<SysUser> entityList);

        SysUser dto2Entity(SysUserDTO dto);

        List<SysUser> dto2Entity(List<SysUserDTO> dtoList);

        SysUser formDto2Entity(SysUserFormDTO formDTO);

        void updateEntityFromDto(SysUserFormDTO formDTO, @MappingTarget SysUser entity);

        @Mappings({
                @Mapping(source = "id", target = "sysUserId")
        })
        SysUserSearchResultDTO entity2SearchResultDto(SysUser entity);

        List<SysUserSearchResultDTO> entity2SearchResultDto(List<SysUser> entityList);
}
