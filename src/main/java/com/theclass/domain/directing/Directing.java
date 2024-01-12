package com.theclass.domain.directing;

import com.theclass.web.dto.DirectingDto;
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
@Table(name="directing")
public class Directing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="directing_id")
    private Long directingId;

    @Column(name="event_id", nullable = false, columnDefinition = "VBICINT(11)")
    private Long eventId;

    @Column(columnDefinition = "VARCHAR(255)")
    private String wplan;

    @Column(columnDefinition = "VARCHAR(255)")
    private String fplan;

    @Column(columnDefinition = "VARCHAR(255)")
    private String cplan;

    @Column(columnDefinition = "VARCHAR(255)")
    private String dplan;

    @Column(columnDefinition = "VARCHAR(255)")
    private String summary;

    @Column(columnDefinition = "VARCHAR(255)")
    private String bill;

    @Builder
    public Directing(Long directingId, Long eventId, String email, String wplan, String fplan, String cplan, String dplan, String summary, String bill){
        this.directingId = directingId;
        this.eventId = eventId;
        this.wplan = wplan;
        this.fplan = fplan;
        this.cplan = cplan;
        this.dplan = dplan;
        this.summary = summary;
        this.bill = bill;
    }

    public void update(DirectingDto dto){
        this.directingId = dto.getDirectingId();
        this.eventId = dto.getEventId();
        this.wplan = dto.getWplan();
        this.fplan = dto.getFplan();
        this.cplan = dto.getCplan();
        this.dplan = dto.getDplan();
        this.summary = dto.getSummary();
        this.bill = dto.getBill();
    }
}
