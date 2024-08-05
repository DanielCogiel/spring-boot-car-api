package com.dcogiel.car_database.services.impl;

import com.dcogiel.car_database.domain.entities.OwnerEntity;
import com.dcogiel.car_database.repositories.OwnerRepository;
import com.dcogiel.car_database.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {
    private OwnerRepository repository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.repository = ownerRepository;
    }

    @Override
    public OwnerEntity createOwner(OwnerEntity owner) {
        return this.repository.save(owner);
    }
}
