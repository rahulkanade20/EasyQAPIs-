package com.EasyQ.API.dto;

import com.EasyQ.API.model.Queue;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface QueueMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQueueFromDto(QueueDto dto, @MappingTarget Queue entity);
}
