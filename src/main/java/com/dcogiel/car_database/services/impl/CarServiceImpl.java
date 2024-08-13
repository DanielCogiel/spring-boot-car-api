package com.dcogiel.car_database.services.impl;

import com.dcogiel.car_database.domain.entities.CarEntity;
import com.dcogiel.car_database.repositories.CarRepository;
import com.dcogiel.car_database.services.CarService;
import com.dcogiel.car_database.util.ObjectPatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository repository;
    private final ObjectPatcher<CarEntity> patcher;

    public CarServiceImpl(CarRepository carRepository, ObjectPatcher<CarEntity> patcher) {
        this.repository = carRepository;
        this.patcher = patcher;
    }

    @Override
    public CarEntity save(CarEntity car, String vin) {
        car.setVin(vin);
        return this.repository.save(car);
    }

    @Override
    public CarEntity partialUpdate(CarEntity carEntity) {
        Optional<CarEntity> retrievedCar = this.repository.findById(carEntity.getVin());

        return retrievedCar
                .map(car -> {
                    try {
                        CarEntity patchedCar = this.patcher.patch(car, carEntity);
                        return this.repository.save(patchedCar);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Failed to patch car", e);
                    }
                }).orElseThrow(() -> new RuntimeException("Car does not exist."));
    }

    @Override
    public List<CarEntity> findAllCars() {
        return StreamSupport
                .stream(this.repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarEntity> findOneCar(String vin) {
        return this.repository.findById(vin);
    }

    @Override
    public boolean exists(String vin) {
        return this.repository.existsById(vin);
    }

    @Override
    public void delete(String vin) {
        this.repository.deleteById(vin);
    }
}
