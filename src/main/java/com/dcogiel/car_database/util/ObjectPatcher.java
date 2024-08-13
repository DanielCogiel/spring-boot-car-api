package com.dcogiel.car_database.util;

import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

@NoArgsConstructor
public class ObjectPatcher<T> {
    public T patch(T targetObj, T sourceObj) throws IllegalAccessException {
        for (Field field : sourceObj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object fieldValue = field.get(sourceObj);
            if (fieldValue != null) field.set(targetObj, fieldValue);
            field.setAccessible(false);
        }
        return targetObj;
    }
}
