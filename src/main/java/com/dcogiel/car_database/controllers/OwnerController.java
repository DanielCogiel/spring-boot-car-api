package com.dcogiel.car_database.controllers;

import com.dcogiel.car_database.domain.dto.OwnerDto;
import com.dcogiel.car_database.domain.entities.OwnerEntity;
import com.dcogiel.car_database.mappers.Mapper;
import com.dcogiel.car_database.services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/owners")
public class OwnerController {
    private OwnerService service;
    private Mapper<OwnerEntity, OwnerDto> mapper;

    public OwnerController(OwnerService ownerService, Mapper<OwnerEntity, OwnerDto> ownerMapper) {
        this.service = ownerService;
        this.mapper = ownerMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return new ResponseEntity<>(
                this.service
                .findAllOwners()
                .stream()
                .map(owner -> this.mapper.mapTo(owner))
                .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OwnerDto> getOneOwner(@PathVariable("id") Long id) {
        Optional<OwnerEntity> ownerEntity = this.service.findOneOwner(id);
        return ownerEntity
            .map(owner -> new ResponseEntity<>(this.mapper.mapTo(owner), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "")
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto owner) {
        OwnerEntity ownerEntity = this.mapper.mapFrom(owner);
        OwnerEntity savedOwnerEntity = this.service.save(ownerEntity);
        return new ResponseEntity<>(this.mapper.mapTo(savedOwnerEntity), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<OwnerDto> fullUpdateOwner(
            @PathVariable("id") Long id,
            @RequestBody OwnerDto owner
    ) {
        if (!this.service.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        OwnerEntity ownerEntity = this.mapper.mapFrom(owner);
        ownerEntity.setId(id);
        OwnerEntity savedOwnerEntity = this.service.save(ownerEntity);
        return new ResponseEntity<>(this.mapper.mapTo(savedOwnerEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<OwnerDto> partialUpdateOwner(
            @PathVariable("id") Long id,
            @RequestBody OwnerDto owner
    ) {
        if (!this.service.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        OwnerEntity ownerEntity = this.mapper.mapFrom(owner);
        ownerEntity.setId(id);
        OwnerEntity savedOwnerEntity = this.service.partialUpdate(ownerEntity);

        return new ResponseEntity<>(this.mapper.mapTo(savedOwnerEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteOwner(@PathVariable("id") Long id) {
        this.service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
