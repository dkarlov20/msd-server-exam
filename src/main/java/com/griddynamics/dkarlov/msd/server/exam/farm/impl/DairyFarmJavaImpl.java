package com.griddynamics.dkarlov.msd.server.exam.farm.impl;

import com.griddynamics.dkarlov.msd.server.exam.container.impl.TreeNodeJava;
import com.griddynamics.dkarlov.msd.server.exam.farm.AbstractDairyFarm;
import com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType;
import org.springframework.stereotype.Service;

import static com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType.JAVA;

@Service
public class DairyFarmJavaImpl extends AbstractDairyFarm {

    public DairyFarmJavaImpl() {
        super(new TreeNodeJava<>(MOTHER_COW_ID, MOTHER_COW));
    }

    @Override
    public DairyFarmType getDairyFarmType() {
        return JAVA;
    }
}
