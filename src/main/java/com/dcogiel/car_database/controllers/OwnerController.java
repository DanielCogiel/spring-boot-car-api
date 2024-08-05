package com.dcogiel.car_database.controllers;

import com.dcogiel.car_database.domain.dto.OwnerDto;
import com.dcogiel.car_database.domain.entities.OwnerEntity;
import com.dcogiel.car_database.mappers.Mapper;
import com.dcogiel.car_database.services.OwnerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/owners")
public class OwnerController {
    private OwnerService service;
    private Mapper<OwnerEntity, OwnerDto> mapper;

    public OwnerController(OwnerService ownerService, Mapper<OwnerEntity, OwnerDto> ownerMapper) {
        this.service = ownerService;
        this.mapper = ownerMapper;
    }

    @PostMapping(path = "")
    public OwnerDto createOwner(@RequestBody OwnerDto owner) {
        OwnerEntity ownerEntity = this.mapper.mapFrom(owner);
        OwnerEntity savedOwnerEntity = this.service.createOwner(ownerEntity);
        return this.mapper.mapTo(savedOwnerEntity);
    }
}
