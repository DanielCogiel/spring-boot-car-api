package com.dcogiel.car_database.repositories;

import com.dcogiel.car_database.domain.entities.OwnerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<OwnerEntity, Long> {}
