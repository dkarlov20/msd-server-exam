package com.griddynamics.dkarlov.msd.server.exam.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Cow {
    private final int cowId;
    private final String nickName;
    @Setter
    private boolean alive;

    @Override
    public String toString() {
        return "Cow(" +
                "cowId = " + cowId +
                ", nickName = '" + nickName + '\'' +
                ", alive = " + alive +
                ')';
    }
}
