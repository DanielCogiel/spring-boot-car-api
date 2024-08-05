package com.dcogiel.car_database.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    private String vin;

    private String model;

    private Integer mileage;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;
}
