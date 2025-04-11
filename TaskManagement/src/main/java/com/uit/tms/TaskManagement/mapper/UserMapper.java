package com.uit.tms.TaskManagement.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.model.AuthResponseDTO;
import com.uit.tms.TaskManagement.model.UserRegisterRequestDTO;
import com.uit.tms.TaskManagement.model.UserResponseDTO;
import com.uit.tms.TaskManagement.model.UserUpdateRequestDTO;

@Mapper(
	    componentModel = MappingConstants.ComponentModel.SPRING,
	    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	    nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
	)
public interface UserMapper {
	AuthResponseDTO toAuthDto(UserEntity entity);
	UserResponseDTO toDto(UserEntity entity);
    UserEntity toEntity(UserRegisterRequestDTO dto);
    void updateEntityFromRegisterDto(UserRegisterRequestDTO dto, @MappingTarget UserEntity entity);
    
    @Mapping(target = "roles", ignore = true)
    void updateEntityFromUpdateRequestDto(UserUpdateRequestDTO dto, @MappingTarget UserEntity entity);
}
