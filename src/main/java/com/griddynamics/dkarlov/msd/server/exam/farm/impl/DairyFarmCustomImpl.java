package com.griddynamics.dkarlov.msd.server.exam.farm.impl;

import com.griddynamics.dkarlov.msd.server.exam.container.impl.TreeNodeCustom;
import com.griddynamics.dkarlov.msd.server.exam.farm.AbstractDairyFarm;
import com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType;
import org.springframework.stereotype.Service;

import static com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType.CUSTOM;

@Service
public class DairyFarmCustomImpl extends AbstractDairyFarm {

    public DairyFarmCustomImpl() {
        super(new TreeNodeCustom<>(MOTHER_COW_ID, MOTHER_COW));
    }

    @Override
    public DairyFarmType getDairyFarmType() {
        return CUSTOM;
    }
}
