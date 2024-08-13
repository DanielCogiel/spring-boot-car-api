package com.dcogiel.car_database.services;

import com.dcogiel.car_database.domain.entities.OwnerEntity;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    OwnerEntity save(OwnerEntity owner);
    OwnerEntity partialUpdate(OwnerEntity owner);
    List<OwnerEntity> findAllOwners();
    Optional<OwnerEntity> findOneOwner(Long id);
    void delete(Long id);
    boolean exists(Long id);
}
