package com.dcogiel.car_database.services.impl;

import com.dcogiel.car_database.domain.entities.OwnerEntity;
import com.dcogiel.car_database.repositories.OwnerRepository;
import com.dcogiel.car_database.services.OwnerService;
import com.dcogiel.car_database.util.ObjectPatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository repository;
    private final ObjectPatcher<OwnerEntity> patcher;

    public OwnerServiceImpl(OwnerRepository ownerRepository, ObjectPatcher<OwnerEntity> patcher) {
        this.repository = ownerRepository;
        this.patcher = patcher;
    }

    @Override
    public OwnerEntity partialUpdate(OwnerEntity owner) {
        Optional<OwnerEntity> ownerToUpdate = this.repository.findById(owner.getId());

        return ownerToUpdate
                .map(ownerTarget -> {
                    try {
                        OwnerEntity patchedOwner = this.patcher.patch(ownerTarget, owner);
                        return this.repository.save(patchedOwner);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Failed to patch owner.");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Owner does not exist."));
    }

    @Override
    public OwnerEntity save(OwnerEntity owner) {
        return this.repository.save(owner);
    }

    @Override
    public Optional<OwnerEntity> findOneOwner(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<OwnerEntity> findAllOwners() {
        return StreamSupport
                .stream(this.repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return this.repository.existsById(id);
    }
}
