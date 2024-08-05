package com.dcogiel.car_database.repositories;

import com.dcogiel.car_database.domain.entities.CarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, String> {}
