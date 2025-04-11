package com.uit.tms.TaskManagement.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.uit.tms.TaskManagement.entity.RoleEntity;
import com.uit.tms.TaskManagement.model.RoleRequestDTO;
import com.uit.tms.TaskManagement.model.RoleResponseDTO;

@Mapper(
	    componentModel = MappingConstants.ComponentModel.SPRING,
	    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	    nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
	)
public interface RoleMapper {
	RoleResponseDTO toDto(RoleEntity entity);
	RoleEntity toEntity(RoleRequestDTO dto);
}
