package com.doodledoodle.backend.global.audit;

@SoftDelete
public interface Auditable {
    BaseTime getBaseTime();
    void setBaseTime(BaseTime baseTime);
}
