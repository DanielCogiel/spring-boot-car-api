package com.dcogiel.car_database.controllers;

import com.dcogiel.car_database.domain.dto.CarDto;
import com.dcogiel.car_database.domain.entities.CarEntity;
import com.dcogiel.car_database.mappers.Mapper;
import com.dcogiel.car_database.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/cars")
public class CarController {
    private CarService service;
    private Mapper<CarEntity, CarDto> mapper;

    public CarController(CarService carService, Mapper<CarEntity, CarDto> carMapper) {
        this.service = carService;
        this.mapper = carMapper;
    }

    @GetMapping(path = "")
    public List<CarDto> getAllCars() {
        return this.service
                .findAllCars()
                .stream()
                .map(this.mapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{vin}")
    public ResponseEntity<CarDto> getOneCar(@PathVariable("vin") String vin) {
        return this.service.findOneCar(vin)
                .map(car -> new ResponseEntity<>(this.mapper.mapTo(car), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{vin}")
    public ResponseEntity<CarDto> createUpdateCar(
            @PathVariable("vin") String vin,
            @RequestBody CarDto car
    ) {
        CarEntity carEntity = this.mapper.mapFrom(car);
        boolean isCarExisting = this.service.exists(vin);
        CarEntity savedCarEntity = this.service.save(carEntity, vin);

        return new ResponseEntity<>(this.mapper.mapTo(savedCarEntity), isCarExisting ? HttpStatus.OK : HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{vin}")
    public ResponseEntity<CarDto> partialUpdateCar(
            @PathVariable("vin") String vin,
            @RequestBody CarDto car
    ) {
        if (!this.service.exists(vin)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        CarEntity carEntity = this.mapper.mapFrom(car);
        carEntity.setVin(vin);
        CarEntity savedCarEntity = this.service.partialUpdate(carEntity);

        return new ResponseEntity<>(this.mapper.mapTo(savedCarEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{vin}")
    public ResponseEntity deleteCar(@PathVariable("vin") String vin) {
        this.service.delete(vin);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
