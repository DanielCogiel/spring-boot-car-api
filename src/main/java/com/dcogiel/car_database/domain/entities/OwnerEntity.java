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
@Table(name = "owners")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owners_id_seq")
    private Long id;

    private String name;

    private String surname;

    private Integer age;
}
