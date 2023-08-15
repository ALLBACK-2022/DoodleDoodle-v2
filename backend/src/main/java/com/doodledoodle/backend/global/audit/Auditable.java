package com.doodledoodle.backend.global.audit;

public interface Auditable {
    BaseTime getBaseTime();

    void setBaseTime(final BaseTime baseTime);
}
