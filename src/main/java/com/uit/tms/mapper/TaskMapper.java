package com.uit.tms.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.uit.tms.entity.TaskEntity;
import com.uit.tms.model.TaskRequestDTO;
import com.uit.tms.model.TaskResponseDTO;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface TaskMapper {
    TaskResponseDTO toDto(TaskEntity task);
    TaskEntity toEntity(TaskRequestDTO taskDTO);
    void updateEntityFromDto(TaskRequestDTO taskDTO, @MappingTarget TaskEntity task);
}
