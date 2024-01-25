package com.theclass.web.dto;

import com.theclass.domain.visit.Visit;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {
    private Long visitId;
    private String visitDate;
    private String visitTime;
    private String visitType;
    private String guestName;
    private String guestHp;
    private String eventDate;
    private String person;
    private String checkMemo;
    private String path;
    private String masterName;
    private String masterDate;
    private String memo;

    @Builder
    public VisitDto(Visit entity){
        this.visitId = entity.getVisitId();
        this.visitTime = entity.getVisitTime();
        this.visitDate = entity.getVisitDate();
        this.visitType = entity.getVisitType();
        this.guestName = entity.getGuestName();
        this.guestHp = entity.getGuestHp();
        this.eventDate = entity.getEventDate();
        this.person = entity.getPerson();
        this.checkMemo = entity.getCheckMemo();
        this.path = entity.getPath();
        this.masterName = entity.getMasterName();
        this.masterDate = entity.getMasterDate();
        this.memo = entity.getMemo();
    }

    public Visit toEntity(){
        return Visit.builder()
                .visitId(visitId)
                .visitDate(visitDate)
                .visitTime(visitTime)
                .visitType(visitType)
                .guestName(guestName)
                .guestHp(guestHp)
                .eventDate(eventDate)
                .person(person)
                .checkMemo(checkMemo)
                .path(path)
                .masterName(masterName)
                .masterDate(masterDate)
                .memo(memo)
                .build();
    }
}
