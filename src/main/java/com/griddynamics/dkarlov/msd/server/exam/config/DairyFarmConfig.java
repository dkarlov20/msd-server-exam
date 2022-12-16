package com.griddynamics.dkarlov.msd.server.exam.config;

import com.griddynamics.dkarlov.msd.server.exam.farm.AbstractDairyFarm;
import com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Configuration
public class DairyFarmConfig {

    @Bean
    public Map<DairyFarmType, AbstractDairyFarm> dairyFarms(List<AbstractDairyFarm> dairyFarms) {
        return dairyFarms.stream()
                .collect(toMap(AbstractDairyFarm::getDairyFarmType, Function.identity()));
    }
}
