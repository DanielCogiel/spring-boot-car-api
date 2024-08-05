package com.dcogiel.car_database.mappers;


public interface Mapper<A, B> {
    B mapTo(A entity);
    A mapFrom(B dto);
}
