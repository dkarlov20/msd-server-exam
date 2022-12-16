package com.griddynamics.dkarlov.msd.server.exam.farm;

import com.griddynamics.dkarlov.msd.server.exam.farm.impl.DairyFarmJavaImpl;
import com.griddynamics.dkarlov.msd.server.exam.model.Cow;
import com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.griddynamics.dkarlov.msd.server.exam.farm.AbstractDairyFarm.MOTHER_COW_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class DairyFarmJavaImplTest {
    private static final int COW_ID_TEST = 1;
    private static final String COW_NICKNAME_TEST = "test";
    private static final Cow COW_TEST = new Cow(COW_ID_TEST, COW_NICKNAME_TEST, true);

    @InjectMocks
    private DairyFarmJavaImpl dairyFarmJava;

    @Test
    public void giveBirthTest() {
        Cow cow = dairyFarmJava.giveBirth(MOTHER_COW_ID, COW_ID_TEST, COW_NICKNAME_TEST);

        assertNotNull(cow);
        assertEquals(COW_TEST, cow);
    }

    @Test
    public void giveBirthToInactiveCowTest() {
        Cow cow = dairyFarmJava.giveBirth(MOTHER_COW_ID, COW_ID_TEST, COW_NICKNAME_TEST);
        cow.setAlive(false);

        Cow child = dairyFarmJava.giveBirth(cow.getCowId(), COW_ID_TEST, COW_NICKNAME_TEST);

        assertNull(child);
    }

    @Test
    public void endLifeSpanTest() {
        Cow cow = dairyFarmJava.giveBirth(MOTHER_COW_ID, COW_ID_TEST, COW_NICKNAME_TEST);

        Cow inactivatedCow = dairyFarmJava.endLifeSpan(cow.getCowId());

        assertNotNull(inactivatedCow);
        assertFalse(cow.isAlive());
    }

    @Test
    public void endLifeSpanWhenCowNotFoundTest() {
        Cow inactivatedCow = dairyFarmJava.endLifeSpan(Integer.MAX_VALUE);

        assertNull(inactivatedCow);
    }

    @Test
    public void getDairyFarmTypeTest() {
        assertEquals(DairyFarmType.JAVA, dairyFarmJava.getDairyFarmType());
    }
}
