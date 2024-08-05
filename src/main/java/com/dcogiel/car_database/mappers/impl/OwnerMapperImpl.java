package com.dcogiel.car_database.mappers.impl;

import com.dcogiel.car_database.domain.dto.OwnerDto;
import com.dcogiel.car_database.domain.entities.OwnerEntity;
import com.dcogiel.car_database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapperImpl implements Mapper<OwnerEntity, OwnerDto> {
    private ModelMapper modelMapper;

    public OwnerMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OwnerDto mapTo(OwnerEntity owner) {
        return this.modelMapper.map(owner, OwnerDto.class);
    }

    @Override
    public OwnerEntity mapFrom(OwnerDto ownerDto) {
        return this.modelMapper.map(ownerDto, OwnerEntity.class);
    }
}
