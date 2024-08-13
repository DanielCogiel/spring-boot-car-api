package com.dcogiel.car_database.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CarDto {
    private String vin;
    private String model;
    private Integer mileage;
    private OwnerDto owner;
}
