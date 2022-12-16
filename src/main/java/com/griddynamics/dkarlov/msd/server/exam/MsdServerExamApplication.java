package com.griddynamics.dkarlov.msd.server.exam;

import com.griddynamics.dkarlov.msd.server.exam.farm.AbstractDairyFarm;
import com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

import static com.griddynamics.dkarlov.msd.server.exam.farm.AbstractDairyFarm.MOTHER_COW_ID;
import static com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType.CUSTOM;
import static com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType.JAVA;

@SpringBootApplication
@RequiredArgsConstructor
public class MsdServerExamApplication implements CommandLineRunner {

    private final Map<DairyFarmType, AbstractDairyFarm> dairyFarms;

    public static void main(String[] args) {
        SpringApplication.run(MsdServerExamApplication.class, args);
    }

    @Override
    public void run(String... args) {
        simulateDairyFarm(dairyFarms.get(JAVA));
        simulateDairyFarm(dairyFarms.get(CUSTOM));
    }

    private static void simulateDairyFarm(AbstractDairyFarm dairyFarm) {
        dairyFarm.giveBirth(MOTHER_COW_ID, 1, "Calf 1");
        dairyFarm.giveBirth(MOTHER_COW_ID, 2, "Calf 2");
        dairyFarm.giveBirth(1, 3, "Calf 3");
        dairyFarm.endLifeSpan(3);
        dairyFarm.giveBirth(3, 4, "Calf 4");
        dairyFarm.giveBirth(2, 5, "Calf 5");
        dairyFarm.giveBirth(2, 6, "Calf 6");
        dairyFarm.printFarmData();
    }
}
