package com.dcogiel.car_database.config;

import com.dcogiel.car_database.util.ObjectPatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatcherConfig {
    @Bean
    public ObjectPatcher objectPatcher() {
        return new ObjectPatcher<>();
    }
}
