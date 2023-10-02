package com.doodledoodle.backend.game.entity;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.global.audit.AuditListener;
import com.doodledoodle.backend.global.audit.Auditable;
import com.doodledoodle.backend.global.audit.BaseTime;
import com.fasterxml.uuid.Generators;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game implements Auditable {

    @Id
    private UUID id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Dictionary dictionary;

    @Column(nullable = false)
    private Integer playerNum;

    @Setter
    @Embedded
    @Column(nullable = false)
    private BaseTime baseTime;

    @Builder
    public Game(final Integer playerNum) {
        this.playerNum = playerNum;
    }

    public void updateDictionary(final Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @PrePersist
    public void createUserUniqId() {
        //sequential uuid 생성
        UUID uuid = Generators.timeBasedGenerator().generate();
        String[] uuidArr = uuid.toString().split("-");
        String uuidStr = uuidArr[2]+uuidArr[1]+uuidArr[0]+uuidArr[3]+uuidArr[4];
        StringBuffer sb = new StringBuffer(uuidStr);
        sb.insert(8, "-");
        sb.insert(13, "-");
        sb.insert(18, "-");
        sb.insert(23, "-");
        uuid = UUID.fromString(sb.toString());
        this.id = uuid;
    }
}
