package com.theclass.domain.visit;

import com.theclass.web.dto.VisitDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visit_id")
    private Long visitId;

    @Column(name = "visit_date", columnDefinition = "VARCHAR(10)")
    private String visitDate;

    @Column(name = "visit_time", columnDefinition = "VARCHAR(10)")
    private String visitTime;

    @Column(name = "visit_type", columnDefinition = "VARCHAR(50)")
    private String visitType;

    @Column(name = "guest_name", columnDefinition = "VARCHAR(50)")
    private String guestName;

    @Column(name = "guest_hp", columnDefinition = "VARCHAR(20)")
    private String guestHp;

    @Column(name = "event_date", columnDefinition = "VARCHAR(10)")
    private String eventDate;

    @Column(columnDefinition = "VARCHAR(30)")
    private String person;

    @Column(name = "check_memo", columnDefinition = "VARCHAR(255)")
    private String checkMemo;

    @Column(columnDefinition = "VARCHAR(100)")
    private String path;

    @Column(name = "master_name", columnDefinition = "VARCHAR(30)")
    private String masterName;

    @Column(name = "master_date", columnDefinition = "VARCHAR(10)")
    private String masterDate;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Builder
    public Visit(Long visitId, String visitDate, String visitTime, String visitType, String guestName, String guestHp,
                 String eventDate, String person, String checkMemo, String path, String masterName, String masterDate, String memo){
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.visitType = visitType;
        this.guestName = guestName;
        this.guestHp = guestHp;
        this.eventDate = eventDate;
        this.person = person;
        this.checkMemo = checkMemo;
        this.path = path;
        this.masterName = masterName;
        this.masterDate = masterDate;
        this.memo = memo;
    }

    public void update(VisitDto dto){
        this.visitId = dto.getVisitId();
        this.visitTime = dto.getVisitTime();
        this.visitDate = dto.getVisitDate();
        this.visitType = dto.getVisitType();
        this.guestName = dto.getGuestName();
        this.guestHp = dto.getGuestHp();
        this.eventDate = dto.getEventDate();
        this.person = dto.getPerson();
        this.checkMemo = dto.getCheckMemo();
        this.path = dto.getPath();
        this.masterName = dto.getMasterName();
        this.masterDate = dto.getMasterDate();
        this.memo = dto.getMemo();
    }
}
