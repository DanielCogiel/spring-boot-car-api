package com.dcogiel.car_database.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OwnerDto {
    private Long id;

    private String name;

    private String surname;

    private Integer age;
}
