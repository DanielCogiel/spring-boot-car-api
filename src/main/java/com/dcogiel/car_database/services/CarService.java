package com.dcogiel.car_database.services;

import com.dcogiel.car_database.domain.entities.CarEntity;

import java.util.List;
import java.util.Optional;

public interface CarService {
    CarEntity save(CarEntity car, String vin);
    CarEntity partialUpdate(CarEntity carEntity);
    List<CarEntity> findAllCars();
    Optional<CarEntity> findOneCar(String vin);
    void delete(String vin);
    boolean exists(String vin);
}
