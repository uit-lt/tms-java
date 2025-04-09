package com.uit.tms.TaskManagement.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.model.AuthRequestDTO;
import com.uit.tms.TaskManagement.model.AuthResponseDTO;

@Mapper(
	    componentModel = MappingConstants.ComponentModel.SPRING,
	    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	    nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
	)
public interface UserMapper {
	AuthResponseDTO toDto(UserEntity entity);
    UserEntity toEntity(AuthRequestDTO dto);
    void updateEntityFromDto(AuthRequestDTO dto, @MappingTarget UserEntity task);
}
