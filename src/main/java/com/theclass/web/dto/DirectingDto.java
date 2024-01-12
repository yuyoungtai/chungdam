package com.theclass.web.dto;

import com.theclass.domain.directing.Directing;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectingDto {
    private Long directingId;
    private Long eventId;
    private String wplan;
    private String fplan;
    private String cplan;
    private String dplan;
    private String summary;
    private String bill;

    @Builder
    public DirectingDto(Directing entity){
        this.directingId = entity.getDirectingId();
        this.eventId = entity.getEventId();
        this.wplan = entity.getWplan();
        this.fplan = entity.getFplan();
        this.cplan = entity.getCplan();
        this.dplan = entity.getDplan();
        this.summary = entity.getSummary();
        this.bill = entity.getBill();
    }

    public Directing toEntity(){
        return Directing.builder()
                .directingId(directingId)
                .eventId(eventId)
                .wplan(wplan)
                .fplan(fplan)
                .cplan(cplan)
                .dplan(dplan)
                .summary(summary)
                .bill(bill)
                .build();
    }
}
