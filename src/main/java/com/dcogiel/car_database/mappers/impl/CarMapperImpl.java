package com.dcogiel.car_database.mappers.impl;

import com.dcogiel.car_database.domain.dto.CarDto;
import com.dcogiel.car_database.domain.entities.CarEntity;
import com.dcogiel.car_database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapperImpl implements Mapper<CarEntity, CarDto> {
    private ModelMapper modelMapper;

    public CarMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CarDto mapTo(CarEntity entity) {
        return this.modelMapper.map(entity, CarDto.class);
    }

    @Override
    public CarEntity mapFrom(CarDto dto) {
        return this.modelMapper.map(dto, CarEntity.class);
    }
}
